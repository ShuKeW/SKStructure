package com.skstructure.demo;

import android.util.Log;
import com.skstructure.interceptor.SKDisplayMethodEndInterceptor;

/**
 * @author weishukai
 * @date 2019/1/2   6:39 PM
 * @describe
 */
public class DisplayEndInterceptor implements SKDisplayMethodEndInterceptor {
    @Override
    public <T> void interceptor(Class<T> tClass, String implName, String methodName, Object[] args, int intercepter) {
        Log.i("tag","DisplayEndInterceptor:  " + methodName + "    value:" + intercepter);
    }
}
