package com.forrest.testrxjava.model;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;

/**
 * Created by forrest on 16/7/29.
 */
public class Value {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
