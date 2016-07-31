package com.forrest.testrxjava.operation;


import com.forrest.testrxjava.util.Log;

import rx.Observable;
import rx.functions.Action1;

/**
 * 制定重复操作的次数
 * Created by forrest on 16/7/18.
 */
public class RepeatOperation implements IOperation {


    @Override
    public void exeCute() {
        Observable.just("110").repeat(2).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("RepeatOperation"," :"+s);
            }
        });
    }
}
