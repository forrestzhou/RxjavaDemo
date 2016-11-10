package com.forrest.testrxjava.operation;


import com.forrest.testrxjava.model.Lesson;
import com.forrest.testrxjava.model.Student;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by forrest on 16/7/18.
 */
public class FlagMapOperation extends BaseOperation {

    @Override
    public void exeCute() {
        super.exeCute();
//        List<Student> students=new ArrayList<>();
//        Student stuent=new Student();
//        Lesson l1=new Lesson();
//        l1.setName("英文");
//        Lesson l2=new Lesson();
//        l2.setName("语文");
//        List<Lesson> lessions=new ArrayList<>();
//        lessions.add(l1);
//        lessions.add(l2);
//        stuent.setLessonList(lessions);
//        students.add(stuent);
//        subscription=Observable.from(students).flatMap(new Func1<Student, Observable<Lesson>>() {
//
//            @Override
//            public Observable<Lesson> call(Student student) {
//                return Observable.from(student.getLessonList());
//            }
//        }).subscribe(new Action1<Lesson>() {
//            @Override
//            public void call(Lesson lesson) {
//                Logger.i("flatMap :"+lesson.getName());
//            }
//        });

//        subscription=Observable.from(students).concatMap(new Func1<Student, Observable<Lesson>>() {
//
//            @Override
//            public Observable<Lesson> call(Student student) {
//                Lesson lesson=new Lesson();
//                lesson.setName("dddddd");
//                return Observable.just(lesson);
//            }
//        }).subscribe(new Action1<Lesson>() {
//            @Override
//            public void call(Lesson lesson) {
//                Logger.i("concatMap :"+lesson.getName());
//
//            }
//        });

//        //flatMap操作符的运行结果
//        Observable.just(10, 20, 30).flatMap(new Func1<Integer, Observable<Integer>>() {
//            @Override
//            public Observable<Integer> call(Integer integer) {
//                //10的延迟执行时间为200毫秒、20和30的延迟执行时间为180毫秒
//                int delay = 200;
//                if (integer > 10)
//                    delay = 180;
//
//                return Observable.from(new Integer[]{integer, integer / 2}).delay(delay, TimeUnit.MILLISECONDS);
//            }
//        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println("flatMap Next:" + integer);
//            }
//        });

        //concatMap操作符的运行结果
//        Observable.just(10, 20, 30).concatMap(new Func1<Integer, Observable<Integer>>() {
//            @Override
//            public Observable<Integer> call(Integer integer) {
//                //10的延迟执行时间为200毫秒、20和30的延迟执行时间为180毫秒
//                int delay = 200;
//                if (integer > 10)
//                    delay = 180;
//
//                return Observable.from(new Integer[]{integer, integer / 2}).delay(delay, TimeUnit.MILLISECONDS);
//            }
//        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println("concatMap Next:" + integer);
//            }
//        });
//
        //switchMap操作符的运行结果
        Observable.just(10, 20, 30).switchMap(new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                //10的延迟执行时间为200毫秒、20和30的延迟执行时间为180毫秒
                int delay = 200;
                if (integer > 10)
                    delay = 180;

                return Observable.from(new Integer[]{integer, integer / 2}).delay(delay, TimeUnit.MILLISECONDS);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("switchMap Next:" + integer);
            }
        });

//        SubscriptionManager.setSubscription(subscription);
    }
}
