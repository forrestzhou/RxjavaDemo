package com.forrest.testrxjava.operation;


import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 周期发送事件
 * Created by forrest on 16/7/18.
 */
public class IntervalOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.interval(2, TimeUnit.SECONDS).map(new Func1<Long, String>() {
            @Override
            public String call(Long aLong) {
                return String.valueOf(aLong).concat(" :seconds");
            }
        }).observeOn(Schedulers.io()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Logger.i(s);
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
