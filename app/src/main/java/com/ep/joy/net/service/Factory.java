package com.ep.joy.net.service;


import com.ep.joy.net.http.HttpClient;
import com.ep.joy.net.utils.Constant;

import java.util.HashMap;
import java.util.Map;


/**
 * author miekoz on 2016/3/21.
 * email  meikoz@126.com
 */
public class Factory {

    public static final TGService provideImgService() {
        return provideService(TGService.class);
    };

    private static Map<Class, Object> m_service = new HashMap();

    public static <T> T provideService(Class cls) {
        Object serv = m_service.get(cls);
        if (serv == null) {
            synchronized (cls) {
                serv = m_service.get(cls);
                if (serv == null) {
                    serv = HttpClient.getIns(Constant.BASE_URL).createService(cls);
                    m_service.put(cls, serv);
                }
            }
        }
        return (T) serv;
    }
}
