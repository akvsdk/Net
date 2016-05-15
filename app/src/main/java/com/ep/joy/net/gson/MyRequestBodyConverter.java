package com.ep.joy.net.gson;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * author  Joy
 * Date:  2016/5/14 0014.
 * version:  V1.0
 * Description: 自定请求格式
 */
public class MyRequestBodyConverter<T> implements Converter<ResponseBody, T> {
    private Type type;
    private Gson mGson = new Gson();

    public MyRequestBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String body = value.string();

            JSONObject json = new JSONObject(body);
            //     boolean status = json.optBoolean("success");
            boolean status = json.optBoolean("status");
            String msg = json.optString("msg", "解析错误");
            // int errorCode = json.optInt("errorCode");
            if (status) {
                //   if (json.has("record")) {
                if (json.has("tngou")) {
                    //  Object data = json.get("record");
                    Object data = json.get("tngou");
                    body = data.toString();
                    return mGson.fromJson(body, type);
                } else {
                    return (T) msg;
                }
            } else {
                throw new RuntimeException(msg);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }
}
