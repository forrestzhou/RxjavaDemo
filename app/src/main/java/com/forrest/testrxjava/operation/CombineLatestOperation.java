package com.forrest.testrxjava.operation;



import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by forrest on 16/7/18.
 *
 */
public class CombineLatestOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();
        //产生0,5,10数列
        Observable<Long> observable1 = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 5;
                    }
                }).take(3);

        //产生0,10,20数列
        Observable<Long> observable2 = Observable.interval(500, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 10;
                    }
                }).take(3);


        subscription=Observable.combineLatest(observable1, observable2, new Func2<Long, Long, Long>() {
            @Override
            public Long call(Long aLong, Long aLong2) {
                System.out.println("aLong: " + aLong+"  aLong2： "+aLong2);

                return aLong+aLong2;
            }
        }).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("Next: " + aLong);
            }
        });

        SubscriptionManager.setSubscription(subscription);
    }
}
