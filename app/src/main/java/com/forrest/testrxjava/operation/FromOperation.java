package com.forrest.testrxjava.operation;


import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by forrest on 16/7/18.
 */
public class FromOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();
        Integer[] item={1,2};
        subscription=Observable.from(item).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Logger.i( "form " + integer);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Logger.e( "error: "+throwable.getMessage());
            }
        }, new Action0() {
            @Override
            public void call() {
                Logger.i("from complete");
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
