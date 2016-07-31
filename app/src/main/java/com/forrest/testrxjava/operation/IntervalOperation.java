package com.forrest.testrxjava.operation;


import android.view.View;
import android.widget.TextView;

import com.forrest.testrxjava.R;
import com.forrest.testrxjava.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 周期发送事件
 * Created by forrest on 16/7/18.
 */
public class IntervalOperation implements IOperation {


    @Override
    public void exeCute() {
        Observable.interval(2, TimeUnit.SECONDS).map(new Func1<Long, String>() {
            @Override
            public String call(Long aLong) {
                return String.valueOf(aLong).concat(" :seconds");
            }
        }).observeOn(Schedulers.io()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(Log.TAG,s);
            }
        });

    }
}
