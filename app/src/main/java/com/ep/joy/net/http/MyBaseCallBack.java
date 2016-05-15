package com.ep.joy.net.http;

import android.content.Context;
import android.widget.Toast;

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

    public MyBaseCallBack(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        onSuccess(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    protected abstract void onSuccess(T result);

}
