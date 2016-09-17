package com.forrest.testrxjava.operation;


import com.forrest.testrxjava.model.Lesson;
import com.forrest.testrxjava.model.Student;
import com.forrest.testrxjava.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by forrest on 16/7/18.
 */
public class ZipOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();
        Observable<Long> l=  Observable.interval(1, TimeUnit.SECONDS).take(5);
        Observable<Long> l1=  Observable.interval(1, TimeUnit.SECONDS).take(1);
        subscription=Observable.zip(l, l1, new Func2<Long, Long, Integer>() {

            @Override
            public Integer call(Long aLong, Long aLong2) {
                return (int) (aLong+aLong);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i(Log.TAG,"number: "+integer);
            }
        });
        SubscriptionManager.setSubscription(subscription);

    }
}
