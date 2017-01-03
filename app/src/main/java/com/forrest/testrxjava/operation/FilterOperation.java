package com.forrest.testrxjava.operation;


import com.orhanobut.logger.Logger;

import java.util.Random;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 过滤条件的数据
 * Created by forrest on 16/7/18.
 */
public class FilterOperation extends BaseOperation {

    private Random random=new Random();


    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for(int i=0;i<10;i++){
                    subscriber.onNext(random.nextInt(20));
                }
            }
        }).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return 10<integer&&integer<15;
            }
        }).observeOn(Schedulers.io()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Logger.i(String.valueOf(integer));
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }



}
