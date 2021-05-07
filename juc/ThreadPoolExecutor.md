# 原理解析(基于JDK1.8.0_77)

先来看一下线程池的构造参数

```java
public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        ...
    }
```

- **corePoolSize**：线程池中的核心线程数；

- **maximumPoolSize**：线程池最大线程数，它表示在线程池中最多能创建多少个线程；

- **keepAliveTime**：线程池中非核心线程闲置超时时长（准确来说应该是没有任务执行时的回收时间，后面会分析）；

  - 一个非核心线程，如果不干活(闲置状态)的时长超过这个参数所设定的时长，就会被销毁掉
  - 如果设置`allowCoreThreadTimeOut(boolean value)`，则也会作用于核心线程

- **TimeUnit**：时间单位。可选的单位有分钟（MINUTES），秒（SECONDS），毫秒(MILLISECONDS) 等；

- **workQueue**：任务的阻塞队列，缓存将要执行的Runnable任务，由各线程轮询该任务队列获取任务执行。可以选择以下几个阻塞队列。

  - ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按 FIFO（先进先出）原则对元素进行排序。
  - LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按FIFO （先进先出） 排序元素，吞吐量通常要高于ArrayBlockingQueue。静态工厂方法`Executors.newFixedThreadPool()`使用了这个队列。
  - SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue，静态工厂方法`Executors.newCachedThreadPool`使用了这个队列。
  - PriorityBlockingQueue：一个具有优先级的无限阻塞队列。

- **ThreadFactory**：线程创建的工厂。可以进行一些属性设置，比如线程名，优先级等等，有默认实现。

  默认实现如下：

  ```java
  static class DefaultThreadFactory implements ThreadFactory {
          private static final AtomicInteger poolNumber = new AtomicInteger(1);
          private final ThreadGroup group;
          private final AtomicInteger threadNumber = new AtomicInteger(1);
          private final String namePrefix;
  
          DefaultThreadFactory() {
              SecurityManager s = System.getSecurityManager();
              group = (s != null) ? s.getThreadGroup() :
                                    Thread.currentThread().getThreadGroup();
              namePrefix = "pool-" +
                            poolNumber.getAndIncrement() +
                           "-thread-";
          }
  
          public Thread newThread(Runnable r) {
              Thread t = new Thread(group, r,
                                    namePrefix + threadNumber.getAndIncrement(),
                                    0);
              if (t.isDaemon())
                  t.setDaemon(false);
              if (t.getPriority() != Thread.NORM_PRIORITY)
                  t.setPriority(Thread.NORM_PRIORITY);
              return t;
          }
      }
  ```

  主要是定义线程名及分组，设置非守护线程及线程等级

- RejectedExecutionHandler

  ：任务拒绝策略，当运行线程数已达到maximumPoolSize，队列也已经装满时会调用该参数拒绝任务，默认情况下是AbortPolicy，表示无法处理新任务时抛出异常。以下是JDK1.5提供的四种策略。

  - AbortPolicy：直接抛出异常。

    这里看一下默认实现的代码：

    ```java
    public static class AbortPolicy implements RejectedExecutionHandler {
            
            public AbortPolicy() { }
    
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                throw new RejectedExecutionException("Task " + r.toString() +
                                                     " rejected from " +
                                                     e.toString());
            }
        }
    ```

    

  - CallerRunsPolicy：只用调用者所在线程来运行任务。

  - DiscardOldestPolicy：丢弃队列里最老的一个任务，并执行当前任务。

  - DiscardPolicy：不处理，丢弃掉。

  - 当然也可以根据应用场景需要来实现`RejectedExecutionHandler`接口自定义策略。如记录日志或持久化不能处理的任务。

  ### ThreadPoolExecutor的状态变量

  ```java
  private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
  private static final int COUNT_BITS = Integer.SIZE - 3;
  private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
  
  // runState is stored in the high-order bits
  private static final int RUNNING    = -1 << COUNT_BITS;
  private static final int SHUTDOWN   =  0 << COUNT_BITS;
  private static final int STOP       =  1 << COUNT_BITS;
  private static final int TIDYING    =  2 << COUNT_BITS;
  private static final int TERMINATED =  3 << COUNT_BITS;
  
  // Packing and unpacking ctl
  private static int runStateOf(int c)     { return c & ~CAPACITY; }
  private static int workerCountOf(int c)  { return c & CAPACITY; }
  private static int ctlOf(int rs, int wc) { return rs | wc; }
  ```

  其中ctl字段是ThreadPoolExecutor的同步状态变量，一共32位，高3位存线程池状态，低29位存线程池数量。

  `workerCountOf()`方法取得当前线程池的线程数量，算法是将ctl的值取低29位。

  `runStateOf()`方法取得线程池的状态，算法是将ctl的值取高3位:

  1. RUNNING 111 表示正在运行
  2. SHUTDOWN 000 表示拒绝接收新的任务
  3. STOP 001 表示拒绝接收新的任务并且不再处理任务队列中剩余的任务，并且中断正在执行的任务。
  4. TIDYING 010 表示所有线程已停止，准备执行terminated()方法。
  5. TERMINATED 011 表示已执行完terminated()方法。

  当我们向线程池提交任务时，通常使用`execute()`方法，接下来就先从该方法开始分析。

  ### execute()方法

  在分析execute代码之前，需要先说明下，我们都知道线程池是维护了一批线程来处理用户提交的任务，达到线程复用的目的，**线程池维护的这批线程被封装成了Worker**。

  ```java
  public void execute(Runnable command) {
          if (command == null)
              throw new NullPointerException();
          /*
           * Proceed in 3 steps:
           *
           * 1. If fewer than corePoolSize threads are running, try to
           * start a new thread with the given command as its first
           * task.  The call to addWorker atomically checks runState and
           * workerCount, and so prevents false alarms that would add
           * threads when it shouldn't, by returning false.
           *
           * 2. If a task can be successfully queued, then we still need
           * to double-check whether we should have added a thread
           * (because existing ones died since last checking) or that
           * the pool shut down since entry into this method. So we
           * recheck state and if necessary roll back the enqueuing if
           * stopped, or start a new thread if there are none.
           *
           * 3. If we cannot queue task, then we try to add a new
           * thread.  If it fails, we know we are shut down or saturated
           * and so reject the task.
           */
          int c = ctl.get();
      	//情况1  当前池中线程比核心数少，新建一个线程执行任务
          if (workerCountOf(c) < corePoolSize) {
              if (addWorker(command, true))
                  return;
              c = ctl.get();
          }
      	//情况2  核心池已满，但任务队列未满，添加到队列中
          if (isRunning(c) && workQueue.offer(command)) {
              int recheck = ctl.get();
              //如果非running状态，就remove，remove成功就拒绝
              if (! isRunning(recheck) && remove(command))
                  reject(command);
              //如果之前的线程已被销毁完，新建一个线程
              else if (workerCountOf(recheck) == 0)
                  addWorker(null, false);
          }
      	//情况3  核心池已满，队列已满，试着创建一个新线程
          else if (!addWorker(command, false))
              //如果创建新线程失败了，说明线程池被关闭或者线程池完全满了，拒绝任务
              reject(command);
    }
  ```

  以上代码对应了三种情况:
  
  1. 线程池的线程数量小于corePoolSize核心线程数量，开启核心线程执行任务。
  2. 线程池的线程数量不小于corePoolSize核心线程数量，或者开启核心线程失败，尝试将任务以非阻塞的方式添加到任务队列，offer到workQueue中后，后续核心线程take方法就不会阻塞了，可以拿到该任务去执行。
  3. 任务队列已满导致添加任务失败，开启新的非核心线程执行任务。

从上面`execute()`的源码可以看出`addWorker()`方法是重中之重，马上来看下它的实现。

### addWorker()方法

```java
 private boolean addWorker(Runnable firstTask, boolean core) {
        retry: //goto 语句,避免死循环
     	//// 使用CAS机制轮询线程池的状态，如果线程池处于SHUTDOWN及大于它的状态则拒绝执行任务
        for (;;) {
            int c = ctl.get();
            int rs = runStateOf(c);

            // Check if queue empty only if necessary.
            if (rs >= SHUTDOWN &&
                ! (rs == SHUTDOWN &&
                   firstTask == null &&
                   ! workQueue.isEmpty()))
                return false;
			//使用CAS机制尝试将当前线程数+1
        	//如果是核心线程当前线程数必须小于corePoolSize 
        	//如果是非核心线程则当前线程数必须小于maximumPoolSize
        	//如果当前线程数小于线程池支持的最大线程数CAPACITY 也会返回失败
            for (;;) {
                int wc = workerCountOf(c);
                if (wc >= CAPACITY ||
                    wc >= (core ? corePoolSize : maximumPoolSize))
                    return false;
                //cas成功，更新工作线程数+1
                if (compareAndIncrementWorkerCount(c))
                    break retry;
                c = ctl.get();  // Re-read ctl
                if (runStateOf(c) != rs)
                    continue retry;
                // else CAS failed due to workerCount change; retry inner loop
            }
        }
		//这里已经成功执行了CAS操作将线程池数量+1，下面创建线程
        boolean workerStarted = false;//工作线程是否启动的标识
        boolean workerAdded = false;//工作线程是否已经添加成功的标识
        Worker w = null;
        try {
            w = new Worker(firstTask);
            //Worker内部有一个Thread，并且执行Worker的run方法，因为Worker实现了Runnable
            final Thread t = w.thread;
            if (t != null) {
                final ReentrantLock mainLock = this.mainLock;
                //这里必须同步在状态为运行的情况下将Worker添加到workers中
                mainLock.lock();
                try {
                    // Recheck while holding lock.
                    // Back out on ThreadFactory failure or if
                    // shut down before lock acquired.
                    int rs = runStateOf(ctl.get());

                    if (rs < SHUTDOWN ||
                        (rs == SHUTDOWN && firstTask == null)) {
                        if (t.isAlive()) // precheck that t is startable
                            throw new IllegalThreadStateException();
                        //把新建的woker线程放入集合保存，这里使用的是HashSet
                        workers.add(w);
                        int s = workers.size();
                        if (s > largestPoolSize)
                            largestPoolSize = s;
                        workerAdded = true;
                    }
                } finally {
                    mainLock.unlock();
                }
                //如果添加成功则运行线程
                if (workerAdded) {
                    t.start();
                    workerStarted = true;
                }
            }
        } finally {
            //如果woker启动失败，则进行一些善后工作，比如说修改当前woker数量等等
            if (! workerStarted)
                addWorkerFailed(w);
        }
        return workerStarted;
    }
```

`addWorker`这个方法先尝试在线程池运行状态为`RUNNING`并且线程数量未达上限的情况下通过CAS操作将线程池数量+1，接着在ReentrantLock同步锁的同步保证下判断线程池为运行状态，然后把Worker添加到HashSet workers中。如果添加成功则执行Worker的内部线程。



### Worker是什么

Worker是ThreadPoolExecutor的内部类，源码如下：

```java
private final class Worker
        extends AbstractQueuedSynchronizer
        implements Runnable
    {
        /**
         * This class will never be serialized, but we provide a
         * serialVersionUID to suppress a javac warning.
         */
        private static final long serialVersionUID = 6138294804551838833L;

        /** Thread this worker is running in.  Null if factory fails. */
        final Thread thread;
        /** Initial task to run.  Possibly null. */
        Runnable firstTask;
        /** Per-thread task counter */
        volatile long completedTasks;

        /**
         * Creates with given first task and thread from ThreadFactory.
         * @param firstTask the first task (null if none)
         */
        Worker(Runnable firstTask) {
            setState(-1); // inhibit interrupts until runWorker
            this.firstTask = firstTask;
            this.thread = getThreadFactory().newThread(this);
        }

        /** Delegates main run loop to outer runWorker  */
        public void run() {
            runWorker(this);
        }

        // Lock methods
        //
        // The value 0 represents the unlocked state.
        // The value 1 represents the locked state.

        protected boolean isHeldExclusively() {
            return getState() != 0;
        }

        protected boolean tryAcquire(int unused) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int unused) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        public void lock()        { acquire(1); }
        public boolean tryLock()  { return tryAcquire(1); }
        public void unlock()      { release(1); }
        public boolean isLocked() { return isHeldExclusively(); }

        void interruptIfStarted() {
            Thread t;
            if (getState() >= 0 && (t = thread) != null && !t.isInterrupted()) {
                try {
                    t.interrupt();
                } catch (SecurityException ignore) {
                }
            }
        }
    }
```

Worker构造方法指定了第一个要执行的任务firstTask，并通过线程池的线程工厂创建线程。可以发现这个线程的参数为this，即Worker对象，因为Worker实现了Runnable因此可以被当成任务执行，执行的即Worker实现的run方法：

```java
public void run() {
    runWorker(this);
}
```

#### runWorker()方法

因为Worker为ThreadPoolExecutor的内部类，因此runWorker方法实际是ThreadPoolExecutor定义的:

```java
final void runWorker(Worker w) {
        Thread wt = Thread.currentThread();
        Runnable task = w.firstTask;
        w.firstTask = null;
    	// 因为Worker的构造函数中setState(-1)禁止了中断，这里的unclock用于恢复中断
        w.unlock(); // allow interrupts
        boolean completedAbruptly = true;
        try {
            //一般情况下，task都不会为空（特殊情况上面注释中也说明了），因此会直接进入循环体中
            while (task != null || (task = getTask()) != null) {
                w.lock();
                // If pool is stopping, ensure thread is interrupted;
                // if not, ensure thread is not interrupted.  This
                // requires a recheck in second case to deal with
                // shutdownNow race while clearing interrupt
                if ((runStateAtLeast(ctl.get(), STOP) ||
                     (Thread.interrupted() &&
                      runStateAtLeast(ctl.get(), STOP))) &&
                    !wt.isInterrupted())
                    wt.interrupt();
                try {
                    //该方法是个空的实现，如果有需要用户可以自己继承该类进行实现
                    beforeExecute(wt, task);
                    Throwable thrown = null;
                    try {
                        //真正的任务执行逻辑
                        task.run();
                    } catch (RuntimeException x) {
                        thrown = x; throw x;
                    } catch (Error x) {
                        thrown = x; throw x;
                    } catch (Throwable x) {
                        thrown = x; throw new Error(x);
                    } finally {
                        //该方法是个空的实现，如果有需要用户可以自己继承该类进行实现
                        afterExecute(task, thrown);
                    }
                } finally {
                    //这里设为null，也就是循环体再执行的时候会调用getTask方法
                    task = null;
                    w.completedTasks++;
                    w.unlock();
                }
            }
            completedAbruptly = false;
        } finally {
            //当指定任务执行完成，阻塞队列中也取不到可执行任务时，会进入这里，做一些善后工作
        	//比如在corePoolSize跟maximumPoolSize之间的woker会进行回收
            processWorkerExit(w, completedAbruptly);
        }
    }
```

这个方法是线程池复用线程的核心代码，注意Worker继承了`AbstractQueuedSynchronizer`，在执行每个任务前通过lock方法加锁，执行完后通过unlock方法解锁，这种机制用来防止运行中的任务被中断。在执行任务时先尝试获取firstTask，即构造方法传入的Runnable对象，然后尝试从`getTask`方法中获取任务队列中的任务。在任务执行前还要再次判断线程池是否已经处于STOP状态或者线程被中断。

在runWorker中，每一个Worker在`getTask()`成功之后都要获取Worker的锁之后运行，也就是说运行中的Worker不会中断。因为核心线程一般在空闲的时候会一直阻塞在获取Task上，也只有中断才可能导致其退出。这些阻塞着的Worker就是空闲的线程（当然，非核心线程阻塞之后也是空闲线程）。如果设置了keepAliveTime>0，那非核心线程会在空闲状态下等待keepAliveTime之后销毁，直到最终的线程数量等于corePoolSize

woker线程的执行流程就是首先执行初始化时分配给的任务，执行完成以后会尝试从阻塞队列中获取可执行的任务，如果指定时间内仍然没有任务可以执行，则进入销毁逻辑调用`processWorkerExit()`方法。
**注：这里只会回收corePoolSize与maximumPoolSize直接的那部分woker**

### getTask()方法

这里`getTask()`方法是要重点说明的，它的实现跟我们构造参数keepAliveTime存活时间有关。我们都知道keepAliveTime代表了线程池中的线程（即woker线程）的存活时间，如果到期则回收woker线程，这个逻辑的实现就在getTask中。

`getTask()`方法就是去阻塞队列中取任务，用户设置的存活时间，就是从这个阻塞队列中取任务等待的最大时间，如果getTask返回null，意思就是woker等待了指定时间仍然没有取到任务，此时就会跳过循环体，进入woker线程的销毁逻辑。

```java
private Runnable getTask() {
    boolean timedOut = false; // Did the last poll() time out?

    for (;;) {
        int c = ctl.get();
        int rs = runStateOf(c);

        // Check if queue empty only if necessary.
        if (rs >= SHUTDOWN && (rs >= STOP || workQueue.isEmpty())) {
            decrementWorkerCount();
            return null;
        }

        int wc = workerCountOf(c);

        // Are workers subject to culling?
        //如果允许线程超时或工作线程大于核心线程，timed为true
        boolean timed = allowCoreThreadTimeOut || wc > corePoolSize;

        if ((wc > maximumPoolSize || (timed && timedOut))
            && (wc > 1 || workQueue.isEmpty())) {
            //减少线程数，并返回null，返回null之后，runWorker方法会退出循环，并移除工作线程worker
            if (compareAndDecrementWorkerCount(c))
                return null;
            continue;
        }

        try {
            //根据超时配置有两种方法取出任务
            Runnable r = timed ?
                //如果过了超时时间没有取到任务，则r为null
                workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
            workQueue.take();
            if (r != null)
                return r;
            //r为null，设置timeOut为true，下一次循环减少核心线程数
            timedOut = true;
        } catch (InterruptedException retry) {
            timedOut = false;
        }
    }
}
```

这个`getTask()`方法通过一个循环不断轮询任务队列有没有任务到来，首先判断线程池是否处于正常运行状态，根据超时配置有两种方法取出任务：

1. BlockingQueue.poll 阻塞指定的时间尝试获取任务，如果超过指定的时间还未获取到任务就返回null。
2. BlockingQueue.take 这种方法会在取到任务前一直阻塞。

FixedThreadPool使用的是take方法，所以会线程会一直阻塞等待任务。CachedThreadPool使用的是poll方法，也就是说CachedThreadPool中的线程如果在60秒内未获取到队列中的任务就会被终止。

到此ThreadPoolExecutor是怎么执行Runnable任务的分析结束。