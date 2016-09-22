package com.forrest.testrxjava.operation;




import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by forrest on 16/7/18.
 */
public class CreateOperation extends BaseOperation {




    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.create(new Observable.OnSubscribe<Integer>(){

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for(int i=0;i<3;i++){
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Logger.i("hello rxjava execute complete");

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Logger.i("hello rxjava "+integer);

            }
        });
        SubscriptionManager.setSubscription(subscription);

//        Observable.create(new Observable.OnSubscribe<Integer>(){
//
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                for(int i=0;i<3;i++){
//                    subscriber.onNext(i);
//                }
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
