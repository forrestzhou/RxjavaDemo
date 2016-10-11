package com.forrest.testrxjava.operation;


import com.forrest.testrxjava.model.Lesson;
import com.forrest.testrxjava.model.Student;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by forrest on 16/7/18.
 */
public class FlagMapOperation extends BaseOperation {

    @Override
    public void exeCute() {
        super.exeCute();
        List<Student> students=new ArrayList<>();
        Student stuent=new Student();
        Lesson l1=new Lesson();
        l1.setName("英文");
        Lesson l2=new Lesson();
        l2.setName("语文");
        List<Lesson> lessions=new ArrayList<>();
        lessions.add(l1);
        lessions.add(l2);
        stuent.setLessonList(lessions);

        Student s1=new Student();
        List<Lesson> lession1=new ArrayList<>();
        lession1.add(l1);
        s1.setLessonList(lession1);

        students.add(stuent);
        students.add(s1);

        subscription=Observable.from(students).flatMap(new Func1<Student, Observable<Lesson>>() {

            @Override
            public Observable<Lesson> call(Student student) {
                return Observable.from(student.getLessonList());
            }
        }).subscribe(new Subscriber<Lesson>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Lesson lesson) {
                Logger.i("flap "+lesson.getName());
            }
        });

        subscription=Observable.from(students).concatMap(new Func1<Student, Observable<Lesson>>() {

            @Override
            public Observable<Lesson> call(Student student) {
                return Observable.from(student.getLessonList());
            }
        }).subscribe(new Subscriber<Lesson>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Lesson lesson) {
                Logger.i("flap "+lesson.getName());
            }
        });


        SubscriptionManager.setSubscription(subscription);
    }
}
