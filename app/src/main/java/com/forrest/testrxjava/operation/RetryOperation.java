package com.forrest.testrxjava.operation;



import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by forrest on 16/7/18.
 */
public class RetryOperation extends BaseOperation  {

    String flag;

    @Override
    public void exeCute() {
        super.exeCute();
        subscription=Observable.just(null).flatMap(new Func1<Object, Observable<String>>() {

            @Override
            public Observable<String> call(Object o) {
                if(flag==null){
                    Logger.i("null..");
                }else {
                    Logger.i("success..");
                }
                return flag==null? Observable.<String>error(new NullPointerException("flag is null")) : getStringTextSuccess() ;
            }
        }).retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Throwable> observable) {
                return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        if (throwable instanceof IllegalArgumentException || throwable instanceof NullPointerException) {
                            return getStringText().doOnNext(new Action1<String>() {
                                @Override
                                public void call(String s) {
                                    flag=s;
                                }
                            });
                        }
                        return Observable.error(throwable);
                    }
                });
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Logger.i(s);
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }

    private  Observable<String> getStringText(){
        return Observable.just(null).map(new Func1<Object, String>() {

            @Override
            public String call(Object o) {
                return "OK";
            }
        });
    }

    private  Observable<String> getStringTextSuccess(){
        return Observable.just(null).map(new Func1<Object, String>() {

            @Override
            public String call(Object o) {
                return "Success final...";
            }
        });
    }
}
