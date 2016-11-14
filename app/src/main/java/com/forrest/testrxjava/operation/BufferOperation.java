package com.forrest.testrxjava.operation;



import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**Buffer
 * Created by forrest on 16/7/18.
 */
public class BufferOperation extends BaseOperation {



    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {

                    final String[] names = {"小-", "小二", "小三", "小四", "小五"};
                    Random random = new Random();
                    if(subscriber.isUnsubscribed()){
                        return;
                    }
                    do {
                        String name = names[random.nextInt(names.length)];
                        subscriber.onNext(name);
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    } while (true);


            }
        }).subscribeOn(Schedulers.io()).buffer(2,TimeUnit.SECONDS).subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> strings) {
                System.out.println("开始两秒点一次名字...");
                for (String name:strings) {
                    System.out.println("name: "+name);
                }
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
