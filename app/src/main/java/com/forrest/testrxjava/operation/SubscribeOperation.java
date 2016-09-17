package com.forrest.testrxjava.operation;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.forrest.testrxjava.R;
import com.forrest.testrxjava.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by forrest on 16/7/21.
 */
public class SubscribeOperation extends BaseOperation {

    private TextView textview;

    private String[] text={"1","2","3"};

    public SubscribeOperation(View view){
        textview= (TextView) view.findViewById(R.id.tv_text);
        textview.setText("初始化...");
    }


    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.from(text).subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                textview.setText("开始玩啦...");
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                textview.setText(s);
                Log.i(Log.TAG,s);
            }
        });
        SubscriptionManager.setSubscription(subscription);
//        Observable.from(text).map(new Func1<String, String>() {
//            @Override
//            public String call(String s) {
//                try {
//                    Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                return s;
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.i(Log.TAG,"s "+s);
//                textview.setText(s);
//            }


//        });

//        Observable.create(new Observable.OnSubscribe<String>() {
//
//
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                for (int i=0;i<4;i++){
//                    try {
//                        subscriber.onNext("currentIndex: "+i);
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                subscriber.onCompleted();
//
//            }
//        }).doOnSubscribe(new Action0() {
//            @Override
//            public void call() {
//                textview.setText("开始啦。。。");
//            }
//        }).subscribeOn(AndroidSchedulers.mainThread()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                try {
//                    Thread.sleep(3000);
//                    textview.setText("结束啦。。。");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                textview.setText(s);
//            }
//        });

    }
}
