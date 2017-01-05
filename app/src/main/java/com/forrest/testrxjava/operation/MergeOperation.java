package com.forrest.testrxjava.operation;



import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by forrest on 16/7/18.
 * 在数据序列的开头增加一项数据
 */
public class MergeOperation extends BaseOperation {


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
                }).take(3);

        //产生0,10,20数列
        Observable<Long> observable2 = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 10;
                    }
                }).take(3);

        subscription=Observable.merge(observable1,observable2).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("out: " + aLong);
            }
        });

        SubscriptionManager.setSubscription(subscription);
    }
}
