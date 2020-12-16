package priv.fandeshan.demo.ribbonuserservice.hystrix;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;

@Component
@Aspect
public class MyHystrixCommandAspect {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Pointcut( value = "@annotation(MyHystrixCommand)")
    public void pointCut(){}

    @Around(value = "pointCut()&&@annotation(hystrixCommand)")
    public Object doPointCut(ProceedingJoinPoint joinPoint,MyHystrixCommand hystrixCommand) throws InterruptedException, ExecutionException, TimeoutException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int timeout = hystrixCommand.timeout();
        Future future = executorService.submit(()->{
            try {
                joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        try {
            return future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            future.cancel(true);
            if (StringUtils.isBlank(hystrixCommand.fallback())) {
                throw e;
            }
            return invokeFallback(joinPoint,hystrixCommand.fallback());
        }

    }

    private Object invokeFallback(ProceedingJoinPoint joinPoint,String fallback) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?>[] parameterTypes = method.getParameterTypes();

        try {
            Method fallbackMethod = joinPoint.getTarget().getClass().getMethod(fallback, parameterTypes);
            fallbackMethod.setAccessible(true);
            return fallbackMethod.invoke(joinPoint.getTarget(),joinPoint.getArgs());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw e;
        }

    }
}
