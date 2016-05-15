package com.ep.joy.net.service;

import com.ep.joy.net.bean.Car;
import com.ep.joy.net.bean.New;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TGService {
//    @Headers("Cache-Control: public, max-age=30")
//    @GET("tnfs/api/list")
//    Call<TG> getImg(@Query("id") int id);

    @Headers("Cache-Control: public, max-age=30")
    @GET("tnfs/api/list")
    Call<List<New>> getImgInfo(@Query("id") int id);

    @Headers("Cache-Control: public, max-age=60")
    @POST("http://222.177.210.200/public/news/getNewsList")
    @FormUrlEncoded
    Call<Car> getCar(@Field("newsTypeVal") String type, @Field("currentPage") String page);

}
