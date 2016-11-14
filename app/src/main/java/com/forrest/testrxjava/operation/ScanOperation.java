package com.forrest.testrxjava.operation;



import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by forrest on 16/7/18.
 */
public class ScanOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();
        Integer numbers[]={1,2,3,4,5,};
        Observable observable=Observable.from(numbers);
        subscription=observable.scan(new Func2<Integer,Integer,Integer>() {
                    @Override
                    public Integer call(Integer sum, Integer item) {
                        System.out.println(" sum: "+sum+"  item: "+item);
                        return sum+item;
                    }
                })
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println(" result: "+o);
                    }
                });
        SubscriptionManager.setSubscription(subscription);
    }
}
