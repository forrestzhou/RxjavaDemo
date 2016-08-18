package com.forrest.testrxjava.operation;


import com.forrest.testrxjava.util.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 抑制（过滤掉）重复的数据项
 * Created by forrest on 16/7/18.
 */
public class DistinctOperation implements IOperation {

    private Random random=new Random();

    @Override
    public void exeCute() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for(int i=0;i<20;i++){
                    subscriber.onNext(random.nextInt(5));
                }
            }
        }).distinct().observeOn(Schedulers.io()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i(Log.TAG,String.valueOf(integer));
            }
        });
    }



}
