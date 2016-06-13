package com.ep.joy.net.http;

import android.content.Context;

import com.ep.joy.net.utils.Toasts;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author  Joy
 * Date:  2016/5/14 0014.
 * version:  V1.0
 * Description:
 */
public abstract class MyBaseCallBack<T> implements Callback<T> {
    protected Context context;

    public MyBaseCallBack() {

    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        onSuccess(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof SocketTimeoutException) {
            Toasts.showShort("网络中断，请检查您的网络状态");
        } else if (t instanceof ConnectException) {
            Toasts.showShort("网络中断，请检查您的网络状态");
        } else {
            Toasts.showShort("网络中断，请检查您的网络状态");
        }

    }

    protected abstract void onSuccess(T result);

}
