package com.ep.joy.net.utils;

import com.ep.joy.net.bean.JsonResult;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * author   Joy
 * Date:  2016/5/17.
 * version:  V1.0
 * Description:
 */
public class RxUtils {



    public static <T> void toSubscribe(Observable<T> observable, Subscriber<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }




    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    public static class HttpResultFunc<T> implements Func1<JsonResult<T>, T> {

        @Override
        public T call(JsonResult<T> httpResult) {
            if (!httpResult.isStatus()) {
                throw new ApiException(100);
            }
            return httpResult.gettngou();
        }
    }
}
