package com.forrest.testrxjava.operation;


import com.orhanobut.logger.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by forrest on 16/7/18.
 */
public class SampleOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    for(int i=1;i<5;i++){
                        subscriber.onNext(i);
                        //时间延迟为0、100、200...900毫秒
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).sample(300,TimeUnit.MILLISECONDS).observeOn(Schedulers.io()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Logger.i(String.valueOf(integer));
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }



}
