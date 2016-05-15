package com.ep.joy.net.http;

import com.ep.joy.net.bean.Car;
import com.ep.joy.net.bean.New;
import com.ep.joy.net.service.Factory;

import java.util.List;

/**
 * author  Joy
 * Date:  2016/5/14 0014.
 * version:  V1.0
 * Description:
 */
public class AppDao {
    private static AppDao mInstance;


    public AppDao() {

    }

    public static synchronized AppDao getInstance() {
        if (mInstance == null) {
            synchronized (AppDao.class) {
                if (null == mInstance) {
                    mInstance = new AppDao();
                }
            }
        }
        return mInstance;
    }


    public void fuck(MyBaseCallBack<Car> callback) {
        Factory.provideImgService().getCar("ac", "1").enqueue(callback);
    }

    public void img(MyBaseCallBack<List<New>> callback) {
        Factory.provideImgService().getImgInfo(5).enqueue(callback);
    }
}
