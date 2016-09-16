package com.forrest.testrxjava.operation;


import com.forrest.testrxjava.model.Lesson;
import com.forrest.testrxjava.model.Student;
import com.forrest.testrxjava.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by forrest on 16/7/18.
 */
public class MapOperation implements IOperation {


    @Override
    public void exeCute() {

//        List<Student> students=new ArrayList<>();
//        Student stuent=new Student();
//        stuent.setName("小一");
//
//        Student s1=new Student();
//        s1.setName("小二");
//
//
//        students.add(stuent);
//        students.add(s1);
//
//        Observable.from(students).map(new Func1<Student, String>() {
//
//            @Override
//            public String call(Student student) {
//                return student.getName();
//            }
//        }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.i(Log.TAG,"MapOperation : "+s);
//            }
//        });

        Observable.create(new Observable.OnSubscribe<Integer>(){

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for(int i=0;i<3;i++){
                    try {
                        subscriber.onNext(i);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).map(new Func1<Integer, String>() {

            @Override
            public String call(Integer integer) {
                return "function ".concat(String.valueOf(integer));
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i(Log.TAG,"hello ".concat(s));
            }
        });

    }
}
