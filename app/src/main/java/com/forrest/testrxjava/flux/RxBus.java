package com.forrest.testrxjava.flux;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * 总线事件和evenbus类似功能
 * Created by forrest on 16/8/15.
 */
public class RxBus {

    private static volatile RxBus instance=new RxBus();

    private final Subject<Object, Object> evenBus = new SerializedSubject<>(PublishSubject.create());

    public void post(Object o){
        evenBus.onNext(o);
    }

    public static RxBus getInstance(){
        return instance;
    }

    public Observable<Object> toObserverable() {
        return evenBus;
    }

}
