package com.skstructure.demo.application;

import com.skstructure.SKApplication;
import com.skstructure.demo.interceptor.DisplayEndInterceptor;
import com.skstructure.demo.interceptor.DisplayStartInterceptor;
import com.skstructure.demo.interceptor.PreEndInterceptor;
import com.skstructure.demo.interceptor.PreStartInterceptor;
import com.skstructure.modules.methodproxy.SKMethodProxy;
import com.skstructure.modules.retrofit2.Retrofit;
import com.skstructure.modules.retrofit2.SKHttpUrl;

import java.util.Map;

/**
 * @author weishukai
 * @date 2019/1/2   4:11 PM
 * @describe
 */
public class DemoApplication extends SKApplication {
    @Override
    public boolean isLogOpen() {
        return true;
    }

    @Override
    public SKMethodProxy createMethodInterceptor(SKMethodProxy.Builder builder) {
        return builder.setPreMethodStartInterceptor(new PreStartInterceptor())
                .setPreMethodEndInterceptor(new PreEndInterceptor())
                .setDisplayMethodStartInterceptor(new DisplayStartInterceptor())
                .setDisplayMethodEndInterceptor(new DisplayEndInterceptor()).build();
    }

    @Override
    public Retrofit createRetrofit(Retrofit.Builder builder) {
        return builder.baseUrl(new SKHttpUrl() {
            @Override
            public String baseUrl() {
                return "https://www.apiopen.top";
            }

            @Override
            public String pathSegments() {
                return null;
            }

            @Override
            public boolean pathSegmentsHead() {
                return false;
            }

            @Override
            public void addHeader() {

            }

            @Override
            public void addPathParam(Map<String, Param> pathParam) {

            }

            @Override
            public void addQueryParam(Map<String, Param> queryParam) {

            }
        }).build();
    }
}
