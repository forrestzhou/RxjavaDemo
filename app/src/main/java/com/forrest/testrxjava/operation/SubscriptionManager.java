package com.forrest.testrxjava.operation;

import rx.Subscription;

/**
 * Created by forrest on 16/8/31.
 */
public class SubscriptionManager {

    private static SubscriptionManager subscriptionManager=new SubscriptionManager();

    public static volatile Subscription subscription ;

    public static void setSubscription(Subscription subscription) {
        SubscriptionManager.subscription = subscription;
    }

    public static void unSubscription(){
        if(subscription!=null&&!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

}
