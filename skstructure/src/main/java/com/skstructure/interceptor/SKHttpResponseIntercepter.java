package com.skstructure.interceptor;

import com.skstructure.modules.retrofit2.Response;

import java.lang.reflect.Method;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/30 11:23
 * @类描述 一句话描述 你的类
 */

public interface SKHttpResponseIntercepter {
    /**
     * response的拦截器
     *
     * @param method   网络请求方法
     * @param response
     * @return
     */
    boolean intercepter(Method method, Response<?> response);
}
