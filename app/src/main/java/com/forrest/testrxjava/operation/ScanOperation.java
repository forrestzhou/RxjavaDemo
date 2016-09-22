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
                        Logger.i(" sum: "+sum+"  item: "+item);

                        return sum+item;
                    }
                })
                .map(new Func1<Integer,String>() {
                    @Override
                    public String call(Integer number) {
                        Logger.i(" number: "+number);
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
                        Logger.i(" result: "+(String)o);
                    }
                });
        SubscriptionManager.setSubscription(subscription);
    }
}
