package com.forrest.testrxjava.model;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by forrest on 16/7/29.
 */
public class SomeType {

    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    public Observable<String> valueObservable() {
        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just(value);
            }
        });
    }

}
