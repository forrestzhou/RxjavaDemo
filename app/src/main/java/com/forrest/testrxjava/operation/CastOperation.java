package com.forrest.testrxjava.operation;


import com.forrest.testrxjava.model.Student;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by forrest on 16/7/18.
 */
public class CastOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();
        String[] strs=new String[]{"1","2","3","4","5"};
        subscription=Observable.from(strs).observeOn(Schedulers.io()).cast(Integer.class).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer: " + integer);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
