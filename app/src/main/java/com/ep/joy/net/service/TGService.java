package com.ep.joy.net.service;

import com.ep.joy.net.bean.JsonResult;
import com.ep.joy.net.bean.New;
import com.ep.joy.net.bean.TG;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface TGService {


//    @Headers("Cache-Control: public, max-age=60")
//    @POST("http://222.177.210.200/public/news/getNewsList")
//    @FormUrlEncoded
//    Call<Car> getCar(@Field("newsTypeVal") String type, @Field("currentPage") String page);


    @Headers("Cache-Control: public, max-age=30")
    @GET("tnfs/api/list")
    Call<TG> getImgInfo(@Query("id") int id);


    @Headers("Cache-Control: public, max-age=30")
    @GET("tnfs/api/list")
    Observable<JsonResult<List<New>>> getImg(@QueryMap Map<String, String> map);

}

