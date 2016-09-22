package com.forrest.testrxjava.operation;


import com.orhanobut.logger.Logger;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Action1;

/**
 * 但不是发射来自原始Observable的数据包，它发射的是Observables
 * Created by forrest on 16/7/18.
 */
public class WindowOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.interval(1, TimeUnit.SECONDS).take(10).
                window(2,TimeUnit.SECONDS).subscribe(new Action1<Observable<Long>>() {
            @Override
            public void call(Observable<Long> longObservable) {

                Logger.i("start...");
                longObservable.subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Logger.i("currentNumber: "+aLong);
                    }
                });
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
