package com.forrest.testrxjava.operation;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by forrest on 16/8/31.
 */
public abstract class BaseOperation implements IOperation {
    protected Subscription subscription;

    @Override
    public void exeCute() {
        SubscriptionManager.unSubscription();
    }

}
