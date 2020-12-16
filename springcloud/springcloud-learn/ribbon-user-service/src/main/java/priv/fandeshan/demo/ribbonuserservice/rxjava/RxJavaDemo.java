package priv.fandeshan.demo.ribbonuserservice.rxjava;


import rx.Observable;
import rx.Observer;
import rx.functions.Action0;

public class RxJavaDemo {

    //ReactiveX Java 响应式编程框架（Android用的比较多）
    // Java stream() java8
    //观察者模式

    public static void main(String[] args) {
        final String[] datas = new String[]{"吃饭","睡觉","打豆豆"};

        final Action0 onCompleted = () -> {
            System.out.println("on Complated");
        };

        Observable<String> observable = Observable.defer(() -> {
            Observable<String> from = Observable.from(datas);
            return from.doOnCompleted(onCompleted);
        });
        Observer observer = new Observer() {

            @Override
            public void onCompleted() {
                System.out.println("Observer: onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Observer: onError");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("Observer: onNext->"+o);
            }
        };
        datas[2] = "不打豆豆";
        observable.subscribe(observer);

    }
}
