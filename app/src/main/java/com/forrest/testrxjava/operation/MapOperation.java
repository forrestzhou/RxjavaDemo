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
public class MapOperation extends BaseOperation {


    @Override
    public void exeCute() {
        super.exeCute();
        List<Student> students=new ArrayList<>();
        Student stuent=new Student();
        stuent.setName("小一");
        Student s1=new Student();
        s1.setName("小二");
        students.add(stuent);
        students.add(s1);
        subscription=Observable.from(students).map(new Func1<Student, String>() {

            @Override
            public String call(Student student) {
                return student.getName();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(Log.TAG,"MapOperation : "+s);
            }
        });
        SubscriptionManager.setSubscription(subscription);
    }
}
