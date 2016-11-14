package com.forrest.testrxjava.operation;


import com.orhanobut.logger.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * Created by forrest on 16/7/18.
 */
public class GroupByOperation extends BaseOperation {


    Random random=new Random();

    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for(int i=0;i<6;i++){
                    int number=random.nextInt(10);
                    System.out.println("key: " + number);
                    subscriber.onNext(number);
                }
            }
        }).groupBy(new Func1<Integer, String>() {
            @Override
            public String call(Integer value) {
                return value % 2 == 0 ? "偶数" : "奇数";
            }
        }).subscribe(new Action1<GroupedObservable<String, Integer>>() {
            @Override
            public void call(final GroupedObservable<String, Integer> stringIntegerGroupedObservable) {
                stringIntegerGroupedObservable.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("key:" + stringIntegerGroupedObservable.getKey() +", value:" + integer);
                    }
                });
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
