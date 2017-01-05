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
public class StartWithOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.just(1,2).startWith(Observable.just(-1)).observeOn(Schedulers.io()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer aLong) {
                Logger.i(String.valueOf(aLong));
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
