package com.ep.joy.net.http;

import com.ep.joy.net.App;
import com.ep.joy.net.gson.HttpCacheInterceptor;
import com.ep.joy.net.gson.MyConverterFactory;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jiongbull.jlog.JLog;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * author  Joy
 * Date:  2016/5/13 0013.
 * version:  V1.0
 * Description:
 */
public class HttpClient {

    private static HttpClient mInstance;
    private Cache cache;
    private Retrofit singleton;

    public static HttpClient getIns(String base_url) {
        if (mInstance == null) {
            synchronized (HttpClient.class) {
                if (mInstance == null) mInstance = new HttpClient(base_url);
            }
        }
        return mInstance;
    }


    public HttpClient(String BASE_URL) {

        File cacheFileDir = App.getContext().getCacheDir();  //指定缓存目录
        File cacheFile = new File(cacheFileDir, "okHttpCache"); //指定缓存文件名
        cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(7676, TimeUnit.MILLISECONDS)
                .connectTimeout(7676, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(new StethoInterceptor())
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .addInterceptor(new LoggingInterceptor())
              //  .addInterceptor(new HttpLoggingInterceptor())
                .cache(cache)
                .build();

        singleton = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //  .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new MyConverterFactory())
                .client(okHttpClient)
                .build();
    }

    class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            JLog.e(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            JLog.e(String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

    public <T> T createService(Class<T> clz) {
        return singleton.create(clz);
    }

}
