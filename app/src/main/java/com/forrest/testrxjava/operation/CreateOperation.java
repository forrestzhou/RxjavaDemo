package com.forrest.testrxjava.operation;


import android.view.View;
import android.widget.TextView;

import com.forrest.testrxjava.R;
import com.forrest.testrxjava.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by forrest on 16/7/18.
 */
public class CreateOperation implements IOperation {

    private TextView textview;

    private String[] text={"1","2","3"};

    public CreateOperation(View view){
        textview= (TextView) view.findViewById(R.id.tv_text);
    }

    @Override
    public void exeCute() {
        Observable.create(new Observable.OnSubscribe<Integer>(){

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for(int i=0;i<3;i++){
                    try {
                        subscriber.onNext(i);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Func1<Integer, String>() {

            @Override
            public String call(Integer integer) {
                textview.setText("function ".concat(String.valueOf(integer)));
                return "function ".concat(String.valueOf(integer));
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(Log.TAG,"hello ".concat(s));
            }
        });


//        Observable.create(new Observable.OnSubscribe<Integer>(){
//
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//               for(int i=0;i<3;i++){
//                   subscriber.onNext(i);
//               }
//                subscriber.onCompleted();
//            }
//        }).subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onCompleted() {
//                Log.i(Log.TAG,"hello rxjava execute complete");
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.i(Log.TAG,"hello rxjava "+integer);
//
//            }
//        });
    }
}
