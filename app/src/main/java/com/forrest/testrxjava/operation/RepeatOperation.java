package com.forrest.testrxjava.operation;


import com.forrest.testrxjava.util.Log;

import rx.Observable;
import rx.functions.Action1;

/**
 * 制定重复操作的次数
 * Created by forrest on 16/7/18.
 */
public class RepeatOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.just("110").repeat(2).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("RepeatOperation"," :"+s);
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
