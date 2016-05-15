package com.ep.joy.net.gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * author  Joy
 * Date:  2016/5/14 0014.
 * version:  V1.0
 * Description:
 */
public class MyConverterFactory extends Converter.Factory {
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new MyRequestBodyConverter<>(type);
    }
}
