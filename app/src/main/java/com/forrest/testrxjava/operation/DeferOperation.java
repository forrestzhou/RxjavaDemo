package com.forrest.testrxjava.operation;

import com.forrest.testrxjava.model.Value;
import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;

/**
 * 被订阅的时候才会调用
 * Created by forrest on 16/7/29.
 */
public class DeferOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();
//        Value instance = new Value();
//        Observable<String> value = instance.valueObservable();
//        instance.setValue("Some Value");
//        subscription=value.subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Logger.i(s);
//            }
//        });

//        Observable<String> observable= Observable.defer(new Func0<Observable<String>>() {
//            @Override
//            public Observable<String> call() {
//                return Observable.just(value.getText());
//            }
//        });

       final Value value=new Value();
        value.setText("小白");
//        Observable<String> observable= Observable.just(value.getText());

        Observable<String> observable= Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just(value.getText());
            }
        });
        value.setText("小黑");

        subscription=observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Logger.i(s);
            }
        });


        SubscriptionManager.setSubscription(subscription);
    }
}
