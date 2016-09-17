package com.forrest.testrxjava.operation;

import com.forrest.testrxjava.model.SomeType;
import com.forrest.testrxjava.util.Log;

import rx.Observable;
import rx.functions.Action1;

/**
 * 被订阅的时候才会调用
 * Created by forrest on 16/7/29.
 */
public class DeferOperation extends BaseOperation {
    private String TAG=DeferOperation.class.getSimpleName();

    @Override
    public void exeCute() {
        super.exeCute();
        SomeType instance = new SomeType();
        Observable<String> value = instance.valueObservable();
        instance.setValue("Some Value");
        subscription=value.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG,": "+s);
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
