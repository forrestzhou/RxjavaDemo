package com.forrest.testrxjava.operation;


import com.forrest.testrxjava.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 发送一个按顺序递增的数列，demo的输出就是以0开始，然后递增的5个数字
 * Created by forrest on 16/7/18.
 */
public class RangeOperation implements IOperation {


    @Override
    public void exeCute() {
        Observable.range(0,5).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i("RangeOperation",String.valueOf(integer));
            }
        });
    }
}
