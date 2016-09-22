package com.forrest.testrxjava.operation;




import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.functions.Action1;


/**
 * 发送一个按顺序递增的数列，demo的输出就是以0开始，然后递增的5个数字
 * Created by forrest on 16/7/18.
 */
public class RangeOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.range(0,5).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Logger.i("RangeOperation",String.valueOf(integer));
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
