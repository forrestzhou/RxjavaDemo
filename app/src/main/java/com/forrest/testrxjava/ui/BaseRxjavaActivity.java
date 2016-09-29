package com.forrest.testrxjava.ui;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by forrest on 16/9/22.
 */

public class BaseRxjavaActivity extends AppCompatActivity {
    public Subscription subscription;


    public void unBindSubscription(){
        if(subscription!=null&&!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindSubscription();
    }
}
