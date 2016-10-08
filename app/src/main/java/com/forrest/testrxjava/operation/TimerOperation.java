package com.forrest.testrxjava.operation;



import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by forrest on 16/7/18.
 */
public class TimerOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.timer(2, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Logger.i(String.valueOf(aLong));
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
