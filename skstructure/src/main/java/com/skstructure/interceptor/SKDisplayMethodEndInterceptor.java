package com.skstructure.interceptor;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/7 17:01
 * @类描述 一句话描述 你的类
 */

public interface SKDisplayMethodEndInterceptor {
    /**
     * @param tClass      这个执行的方法所述的接口
     * @param implName    实现类的名字
     * @param methodName  方法名字
     * @param args        方法参数
     * @param intercepter 注解的值
     * @param <T>
     * @return
     */
    <T> void interceptor(Class<T> tClass, String implName, String methodName, Object[] args, int intercepter);
}
