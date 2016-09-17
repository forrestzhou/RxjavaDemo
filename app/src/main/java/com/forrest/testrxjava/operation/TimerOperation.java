package com.forrest.testrxjava.operation;


import com.forrest.testrxjava.util.Log;

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
        Observable.timer(2, TimeUnit.SECONDS).map(new Func1<Long, String>() {
            @Override
            public String call(Long aLong) {
                return "2 seconds";
            }
        }).observeOn(Schedulers.io()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(Log.TAG,"s: ".concat(s));
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
