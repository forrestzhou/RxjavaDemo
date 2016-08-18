package com.forrest.testrxjava.operation;


import com.forrest.testrxjava.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * 但不是发射来自原始Observable的数据包，它发射的是Observables
 * Created by forrest on 16/7/18.
 */
public class WindowOperation implements IOperation {


    @Override
    public void exeCute() {
        Observable.interval(1, TimeUnit.SECONDS).take(10).
                window(2,TimeUnit.SECONDS).subscribe(new Action1<Observable<Long>>() {
            @Override
            public void call(Observable<Long> longObservable) {

                Log.i(Log.TAG,"start...");
                longObservable.subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.i(Log.TAG,"currentNumber: "+aLong);
                    }
                });
            }
        });
    }
}
