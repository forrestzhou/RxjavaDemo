package com.forrest.testrxjava.operation;


import com.forrest.testrxjava.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by forrest on 16/7/18.
 */
public class FromOperation implements IOperation {


    @Override
    public void exeCute() {
        Integer[] item={1,2,3};

        Observable.from(item).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i(Log.TAG, "form " + integer);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(Log.TAG, "error: "+throwable.getMessage());
            }
        }, new Action0() {
            @Override
            public void call() {
                Log.i(Log.TAG,"from complete");
            }
        });
    }
}
