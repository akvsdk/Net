package com.ep.joy.net.utils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Events<T> {

    //所有事件的CODE
    public static final int SEND4 = 1; //点击事件
    public static final int DO = 110; //点击事件
    public static final int OTHER = 21; //其他事件

    //枚举
    @IntDef({SEND4, OTHER, DO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EventCode {
    }


    public
    @Events.EventCode
    int code;
    public T content;

    public static <O> Events<O> setContent(O t) {
        Events<O> events = new Events<>();
        events.content = t;
        return events;
    }

    public <T> T getContent() {
        return (T) content;
    }

}