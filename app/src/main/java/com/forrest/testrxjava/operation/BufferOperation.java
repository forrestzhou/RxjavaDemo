package com.forrest.testrxjava.operation;


import com.forrest.testrxjava.R;
import com.forrest.testrxjava.util.Log;

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

    private String[] names={"小-","小二","小三","小四","小五"};


    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if(subscriber!=null&&subscriber.isUnsubscribed()){
                        return;
                    }
                    Random random=new Random();
                    do{
                        String name=names[random.nextInt(names.length)];
                        subscriber.onNext(name);
                        Thread.sleep(1000);
                    }while (true);
                }catch (Exception e){
                    subscriber.onError(e);
                }

            }
        }).subscribeOn(Schedulers.io()).buffer(2,TimeUnit.SECONDS).subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> strings) {
                Log.i("BufferOperation","开始两秒点一次名字...");
                for (String name:strings) {
                    Log.i("BufferOperation","name: "+name);
                }
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
