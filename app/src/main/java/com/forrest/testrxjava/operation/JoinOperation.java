package com.forrest.testrxjava.operation;



import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by forrest on 16/7/18.
 * 在数据序列的开头增加一项数据
 */
public class JoinOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();

        //产生0,5数列
        Observable<Long> observable1 = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 5;
                    }
                }).take(2);

        //产生0,10,20数列
        Observable<Long> observable2 = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 10;
                    }
                }).take(3);

        subscription=observable1.join(observable2, new Func1<Long, Observable<Long>>() {
            @Override
            public Observable<Long> call(Long aLong) {
                return Observable.just(aLong).delay(500, TimeUnit.MILLISECONDS);
            }
        }, new Func1<Long, Observable<Long>>() {
            @Override
            public Observable<Long> call(Long aLong) {
                return Observable.just(aLong).delay(1000, TimeUnit.MILLISECONDS);
            }
        }, new Func2<Long, Long, Long>() {
            @Override
            public Long call(Long aLong, Long aLong2) {
                System.out.println("aLong: " + aLong+"  aLong2： "+aLong2);
                return aLong + aLong2;
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
