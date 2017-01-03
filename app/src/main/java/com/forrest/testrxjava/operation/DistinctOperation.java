package com.forrest.testrxjava.operation;


import com.orhanobut.logger.Logger;

import java.util.Random;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 抑制（过滤掉）重复的数据项
 * Created by forrest on 16/7/18.
 */
public class DistinctOperation extends BaseOperation {

    private Random random=new Random();

    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for(int i=0;i<10;i++){
                    subscriber.onNext(random.nextInt(3));
                }
            }
        }).distinct().observeOn(Schedulers.io()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Logger.i(String.valueOf(integer));
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }



}
