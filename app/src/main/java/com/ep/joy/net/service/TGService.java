package com.ep.joy.net.service;

import com.ep.joy.net.bean.JsonResult;
import com.ep.joy.net.bean.New;
import com.ep.joy.net.bean.TG;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

public interface TGService {

    @Headers("Cache-Control: public, max-age=30")
    @GET("tnfs/api/list")
    Call<TG> getImgInfo(@Query("id") int id);


    @Headers("Cache-Control: public, max-age=30")
    @GET("tnfs/api/list")
    Observable<JsonResult<List<New>>> getImg (@Query("id") int id);


}
