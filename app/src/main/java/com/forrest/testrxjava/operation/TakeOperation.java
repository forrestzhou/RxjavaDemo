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
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

/**
 * 输出前6个数字
 * Created by forrest on 16/7/18.
 */
public class TakeOperation extends BaseOperation  {

    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.interval(1, TimeUnit.SECONDS).take(6).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.i("TakeOperation",String.valueOf(aLong));
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
