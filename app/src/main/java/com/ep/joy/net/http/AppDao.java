package com.ep.joy.net.http;

import com.ep.joy.net.bean.New;
import com.ep.joy.net.bean.TG;
import com.ep.joy.net.service.Factory;
import com.ep.joy.net.utils.RxUtils;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

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


    public void getRxImg(Subscriber<List<New>> subscriber, int id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        map.put("row", "3");
        Observable observable = Factory.provideImgService().getImg(HttpClient.getCacheControl(), map)
                .map(new RxUtils.HttpResultFunc<List<New>>());
        RxUtils.toSubscribe(observable, subscriber);
    }

    public void getimg(MyBaseCallBack<TG> callback) {
        Factory.provideImgService().getImgInfo(5).enqueue(callback);
    }


}
