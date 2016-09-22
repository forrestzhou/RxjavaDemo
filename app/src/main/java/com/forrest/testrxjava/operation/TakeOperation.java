package com.forrest.testrxjava.operation;



import com.orhanobut.logger.Logger;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Action1;


/**
 * 输出前6个数字
 * Created by forrest on 16/7/18.
 */
public class TakeOperation extends BaseOperation  {

    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.interval(1, TimeUnit.SECONDS).take(6).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Logger.i("TakeOperation",String.valueOf(aLong));
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
