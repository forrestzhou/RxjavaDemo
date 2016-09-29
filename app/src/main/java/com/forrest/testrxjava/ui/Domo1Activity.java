package com.forrest.testrxjava.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.forrest.testrxjava.R;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Domo1Activity extends BaseRxjavaActivity {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.tv_text)
    TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domo1);
    }

    @OnClick(R.id.btn1) void onButton1Click(){
        unBindSubscription();
        subscription= Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Logger.i("执行耗时操作....");
                try {
                    Thread.sleep(5000);
                    subscriber.onNext("耗时操作完成...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Logger.i(s);
            }
        });
    }

    @OnClick(R.id.btn2) void onButton2Click(){
        unBindSubscription();
        subscription= Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                Logger.i("doInBackground()....");
                try {
                    Thread.sleep(5000);
                    subscriber.onNext("耗时操作完成...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            //不会影响doOnSubscribe的线程
        }).subscribeOn(Schedulers.io()).
                doOnSubscribe(new Action0() {
            @Override
            public void call() {
                progressBar.setVisibility(View.VISIBLE);
                Logger.i("onPreExecute() ....");
            }
           //会影响doOnSubscribe运行的线程
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                //doOnSubscribe默认会运行在subscribe()所在的线程
                .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Logger.i("onPostExecute .... ".concat(s));
                progressBar.setVisibility(View.GONE);
            }
        });
    }




}
