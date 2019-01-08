package com.skstructure.modules.methodproxy;

import com.skstructure.SKHelper;
import com.skstructure.interceptor.SKDisplayMethodEndInterceptor;
import com.skstructure.interceptor.SKDisplayMethodStartInterceptor;
import com.skstructure.interceptor.SKPreMethodEndInterceptor;
import com.skstructure.interceptor.SKPreMethodStartInterceptor;
import com.skstructure.modules.log.L;
import com.skstructure.utils.SKAppUtil;
import com.skstructure.utils.SKCheckUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @创建人 weishukai
 * @创建时间 2018/2/28 15:47
 * @类描述 一句话描述 你的类
 */

public class SKMethodProxy {
    SKPreMethodStartInterceptor preMethodStartInterceptor;
    SKPreMethodEndInterceptor preMethodEndInterceptor;
    SKDisplayMethodStartInterceptor displayMethodStartInterceptor;
    SKDisplayMethodEndInterceptor displayMethodEndInterceptor;

    public SKMethodProxy() {
        this(new Builder());
    }

    public SKMethodProxy(Builder builder) {
        this.preMethodStartInterceptor = builder.preMethodStartInterceptor;
        this.preMethodEndInterceptor = builder.preMethodEndInterceptor;
        this.displayMethodStartInterceptor = builder.displayMethodStartInterceptor;
        this.displayMethodEndInterceptor = builder.displayMethodEndInterceptor;
    }

    /**
     * 创建presenter代理
     *
     * @param tClass
     * @param impl
     * @param <T>
     */
    public <T> SKProxy createPreProxy(final Class<T> tClass, final Object impl) {
        final SKProxy skProxy = new SKProxy();
        skProxy.impl = impl;
        skProxy.proxy =
                Proxy.newProxyInstance(tClass.getClassLoader(), new Class<?>[]{tClass}, new SKInvocationHandler() {
                    @Override
                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                        /**
                         * 如果有返回值直接执行
                         */
                        if (!method.getReturnType().equals(void.class)) {
                            return method.invoke(skProxy.impl, objects);
                        }
                        SKMethod skMethod = loadPreMethod(skProxy, tClass, impl, method);
                        return skMethod.invoke(objects);
                    }
                });
        return skProxy;
    }

    private <T> SKMethod<T> loadPreMethod(SKProxy skProxy, Class<T> tClass, Object impl, Method method) {
        synchronized (skProxy.methodCache) {
            String methodKey = getKey(method, method.getParameterTypes());
            SKMethod skMethod = skProxy.methodCache.get(methodKey);
            if (skMethod == null) {
                skMethod = SKMethod.createPreMethod(tClass, impl, method);
                skProxy.methodCache.put(methodKey, skMethod);
            }
            return skMethod;
        }
    }

    public <T> T createNullProxy(final Class<T> tClass) {
        SKCheckUtil.checkIsInterface(tClass);
        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class<?>[]{tClass}, new SKInvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if (SKHelper.getSkApplication().isLogOpen()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("UI被销毁,回调接口继续执行");
                    stringBuilder.append("方法[");
                    stringBuilder.append(method.getName());
                    stringBuilder.append("]");
                    L.tag(tClass.getSimpleName());
                    L.i(stringBuilder.toString());
                }

                if (method.getReturnType().equals(int.class) || method.getReturnType().equals(long.class) || method.getReturnType().equals(float.class) || method.getReturnType().equals(double.class)
                        || method.getReturnType().equals(short.class)) {
                    return 0;
                }

                if (method.getReturnType().equals(boolean.class)) {
                    return false;
                }
                if (method.getReturnType().equals(byte.class)) {
                    return Byte.parseByte(null);
                }
                if (method.getReturnType().equals(char.class)) {
                    return ' ';
                }
                return null;
            }
        });
    }

    public <T> T createUIProxy(Class<T> tClass, final Object impl) {

        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class<?>[]{tClass}, new SKInvocationHandler() {
            @Override
            public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
                /**
                 * 有返回值，直接执行
                 */
                if (!method.getReturnType().equals(void.class)) {
                    if (impl != null) {
                        return method.invoke(impl, args);
                    } else {
                        return null;
                    }
                }
                /**
                 * 如果是在主线程，直接执行
                 */
                if (SKAppUtil.isMainThread()) {
                    if (impl != null) {
                        return method.invoke(impl, args);
                    } else {
                        return null;
                    }
                }
                /**
                 * 否则发送回主线程执行
                 */
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (impl != null) {
                                method.invoke(impl, args);
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                };

                SKHelper.getSkMainExecutor().execute(runnable);
                return null;
            }
        });
    }


    /**
     * 获取方法唯一标记
     *
     * @param method
     * @param classes
     * @return
     */
    private String getKey(Method method, Class[] classes) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method.getName());
        stringBuilder.append("(");
        for (Class clazz : classes) {
            stringBuilder.append(clazz.getSimpleName());
            stringBuilder.append(",");
        }
        if (stringBuilder.charAt(stringBuilder.length() - 1) == ',') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /**
     * 创建display的代理
     *
     * @param tClass
     * @param impl
     * @param <D>
     * @return
     */
    public <D> SKProxy createDisplayProxy(final Class<D> tClass, final Object impl) {
        final SKProxy skProxy = new SKProxy();
        skProxy.impl = impl;
        skProxy.proxy =
                Proxy.newProxyInstance(tClass.getClassLoader(), new Class<?>[]{tClass}, new SKInvocationHandler() {
                    @Override
                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                        /**
                         * 如果有返回值直接执行
                         */
                        if (!method.getReturnType().equals(void.class)) {
                            return method.invoke(skProxy.impl, objects);
                        }
                        SKMethod skMethod = loadDisplayMethod(skProxy, tClass, impl, method);
                        return skMethod.invoke(objects);
                    }
                });
        return skProxy;
    }

    private <T> SKMethod<T> loadDisplayMethod(SKProxy skProxy, Class<T> tClass, Object impl, Method method) {
        synchronized (skProxy.methodCache) {
            String methodKey = getKey(method, method.getParameterTypes());
            SKMethod skMethod = skProxy.methodCache.get(methodKey);
            if (skMethod == null) {
                skMethod = SKMethod.createDisplayMethod(tClass, impl, method);
                skProxy.methodCache.put(methodKey, skMethod);
            }
            return skMethod;
        }
    }

    /**
     * builder模式
     */
    public static final class Builder {
        private SKPreMethodStartInterceptor preMethodStartInterceptor;
        private SKPreMethodEndInterceptor preMethodEndInterceptor;
        private SKDisplayMethodStartInterceptor displayMethodStartInterceptor;
        private SKDisplayMethodEndInterceptor displayMethodEndInterceptor;

        public Builder() {

        }

        public Builder setPreMethodStartInterceptor(SKPreMethodStartInterceptor preMethodStartInterceptor) {
            this.preMethodStartInterceptor = preMethodStartInterceptor;
            return this;
        }

        public Builder setPreMethodEndInterceptor(SKPreMethodEndInterceptor preMethodEndInterceptor) {
            this.preMethodEndInterceptor = preMethodEndInterceptor;
            return this;
        }

        public Builder setDisplayMethodStartInterceptor(SKDisplayMethodStartInterceptor displayMethodStartInterceptor) {
            this.displayMethodStartInterceptor = displayMethodStartInterceptor;
            return this;
        }

        public Builder setDisplayMethodEndInterceptor(SKDisplayMethodEndInterceptor displayMethodEndInterceptor) {
            this.displayMethodEndInterceptor = displayMethodEndInterceptor;
            return this;
        }

        public SKMethodProxy build() {
            return new SKMethodProxy(this);
        }

    }
}
