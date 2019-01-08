package com.skstructure.demo;

import android.util.Log;
import com.skstructure.interceptor.SKDisplayMethodEndInterceptor;
import com.skstructure.interceptor.SKPreMethodEndInterceptor;
import com.skstructure.interceptor.SKPreMethodStartInterceptor;

/**
 * @author weishukai
 * @date 2019/1/2   6:39 PM
 * @describe
 */
public class PreEndInterceptor implements SKPreMethodEndInterceptor {
    @Override
    public <T> void interceptor(Class<T> tClass, String implName, String methodName, Object[] args, int intercepter) {
        Log.i("tag","PreEndInterceptor:  " + methodName + "    value:" + intercepter);
    }
}
