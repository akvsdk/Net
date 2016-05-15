package com.ep.joy.net;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.jiongbull.jlog.JLog;

/**
 * author  Joy
 * Date:  2016/5/13 0013.
 * version:  V1.0
 * Description:
 */
public class App extends Application {
    private static Context mContext;


    public static Context getContext() {
        return mContext;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Stetho.initializeWithDefaults(this);
        JLog.init(this)
                .setDebug(BuildConfig.DEBUG);
    }
}
