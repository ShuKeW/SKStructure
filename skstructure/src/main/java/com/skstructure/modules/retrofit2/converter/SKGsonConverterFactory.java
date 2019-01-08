package com.skstructure.modules.retrofit2.converter;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.skstructure.modules.retrofit2.Converter;
import com.skstructure.modules.retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/16 10:44
 * @类描述 一句话描述 你的类
 */

public class SKGsonConverterFactory extends Converter.Factory {
    private final Gson gson;

    public SKGsonConverterFactory(Gson gson) {
        this.gson = gson;
    }

    public static SKGsonConverterFactory create() {
        return create(new Gson());
    }

    public static SKGsonConverterFactory create(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }
        return new SKGsonConverterFactory(gson);
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> typeAdapter = gson.getAdapter(TypeToken.get(type));
        return new SKGsonResponseBodyConverter<>(gson, typeAdapter);
    }
}
