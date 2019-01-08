package com.skstructure.demo;

import android.util.Log;
import com.skstructure.interceptor.SKDisplayMethodStartInterceptor;

/**
 * @author weishukai
 * @date 2019/1/2   6:38 PM
 * @describe
 */
public class DisplayStartInterceptor implements SKDisplayMethodStartInterceptor {
    @Override
    public <T> boolean interceptor(Class<T> tClass, String implName, String methodName, Object[] args, int intercepter) {
        Log.i("tag","DisplayStartInterceptor:  " + methodName + "    value:" + intercepter);
        return false;
    }
}
