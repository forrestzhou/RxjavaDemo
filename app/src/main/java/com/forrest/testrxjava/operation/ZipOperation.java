package com.forrest.testrxjava.operation;



import com.orhanobut.logger.Logger;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by forrest on 16/7/18.
 */
public class ZipOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();
        Observable<Long> observable1=  Observable.interval(1, TimeUnit.SECONDS).take(3);
        Observable<Long> observable2=  Observable.interval(1, TimeUnit.SECONDS).take(4);
        subscription=Observable.zip(observable1, observable2, new Func2<Long, Long, Integer>() {

            @Override
            public Integer call(Long aLong, Long aLong2) {
                System.out.println("aLong:" + aLong+"   aLong2：  "+aLong2);

                return (int) (aLong+aLong);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("result:" + integer);
            }
        });
        SubscriptionManager.setSubscription(subscription);

    }
}
