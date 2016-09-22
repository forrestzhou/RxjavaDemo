package com.forrest.testrxjava.operation;



import com.orhanobut.logger.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by forrest on 16/7/18.
 */
public class DebounceOperation extends BaseOperation {

    private Random random=new Random();

    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    for(int i=0;i<10;i++){
                        subscriber.onNext(i);
                        Thread.sleep(random.nextInt(10)*100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).debounce(300,TimeUnit.MILLISECONDS).observeOn(Schedulers.io()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Logger.i(String.valueOf(integer));
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }



}
