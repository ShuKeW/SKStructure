package com.skstructure.demo;

import com.skstructure.SKApplication;
import com.skstructure.modules.methodproxy.SKMethodProxy;
import com.skstructure.modules.retrofit2.Retrofit;
import com.skstructure.modules.retrofit2.SKHttpUrl;

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
            public void addPathParam() {

            }

            @Override
            public void addQueryParam() {

            }
        }).build();
    }
}
