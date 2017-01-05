package com.forrest.testrxjava.operation;



import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by forrest on 16/7/18.
 * 在数据序列的开头增加一项数据
 */
public class SwitchOnNextOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();

        Observable<Observable<Long>> observable = Observable.interval(500, TimeUnit.MILLISECONDS).map(new Func1<Long, Observable<Long>>() {
            @Override
            public Observable<Long> call(Long aLong) {
                //每隔200毫秒产生一组数据（0,10,20,30,40)
                return Observable.interval(200, TimeUnit.MILLISECONDS).map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 10;
                    }
                }).take(3);
            }
        }).take(2);

        subscription=Observable.switchOnNext(observable).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("Next:" + aLong);
            }
        });

        SubscriptionManager.setSubscription(subscription);
    }
}
