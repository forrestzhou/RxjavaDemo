package com.forrest.testrxjava.operation;



import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by forrest on 16/7/18.
 */
public class JustOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.just("one","two","three").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Logger.i("just complete..");

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Logger.i("just "+s);
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
