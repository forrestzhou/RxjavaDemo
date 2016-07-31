package com.forrest.testrxjava.operation;


import com.forrest.testrxjava.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

/**
 * Created by forrest on 16/7/18.
 */
public class GroupByOperation implements IOperation {


    @Override
    public void exeCute() {
        Observable.interval(1, TimeUnit.SECONDS).take(10).groupBy(new Func1<Long, Long>() {
            @Override
            public Long call(Long value) {
                //按照key为0,1,2分为3组
                return value % 3;
            }
        }).subscribe(new Action1<GroupedObservable<Long, Long>>() {
            @Override
            public void call(final GroupedObservable<Long, Long> result) {
                result.subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long value) {
                        Log.i("GroupByOperation","key:" + result.getKey() +", value:" + value);
                    }
                });
            }
        });

    }
}
