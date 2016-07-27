package com.forrest.testrxjava.operation;


import com.forrest.testrxjava.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by forrest on 16/7/18.
 */
public class JustOperation implements IOperation {


    @Override
    public void exeCute() {
        Observable.just("one","two","three").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(Log.TAG,"just complete..");

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i(Log.TAG,"just "+s);
            }
        });
    }
}
