package com.skstructure.modules.retrofit2.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.skstructure.modules.retrofit2.Converter;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/16 10:45
 * @类描述 一句话描述 你的类
 */

public class SKGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> typeAdapter;

    public SKGsonResponseBodyConverter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.gson = gson;
        this.typeAdapter = typeAdapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            return typeAdapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
