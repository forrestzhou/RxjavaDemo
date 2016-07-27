package com.forrest.testrxjava.operation;


import android.nfc.Tag;

import com.forrest.testrxjava.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by forrest on 16/7/18.
 */
public class ScanOperation implements IOperation {


    @Override
    public void exeCute() {
        Integer numbers[]={1,2,3,4,5,};
        Observable observable=Observable.from(numbers);
        observable
                .scan(new Func2<Integer,Integer,Integer>() {
                    @Override
                    public Integer call(Integer sum, Integer item) {
                        Log.i(Log.TAG," sum: "+sum+"  item: "+item);

                        return sum+item;
                    }
                })
                .map(new Func1<Integer,String>() {
                    @Override
                    public String call(Integer number) {
                        Log.i(Log.TAG," number: "+number);
                        return String.valueOf(number);
                    }
                })
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Log.i(Log.TAG," result: "+(String)o);
                    }
                });
    }
}
