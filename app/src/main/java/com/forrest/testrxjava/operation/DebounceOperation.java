package com.forrest.testrxjava.operation;


import android.content.Intent;

import com.forrest.testrxjava.util.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by forrest on 16/7/18.
 */
public class DebounceOperation extends BaseOperation {

    private Random random=new Random();

    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    for(int i=0;i<10;i++){
                        subscriber.onNext(i);
                        Thread.sleep(random.nextInt(10)*100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).debounce(300,TimeUnit.MILLISECONDS).observeOn(Schedulers.io()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i(Log.TAG,String.valueOf(integer));
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }



}
