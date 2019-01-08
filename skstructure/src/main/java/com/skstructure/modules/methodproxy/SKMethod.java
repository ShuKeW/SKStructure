package com.skstructure.modules.methodproxy;

import android.util.Log;
import com.skstructure.SKHelper;
import com.skstructure.modules.log.L;
import com.skstructure.modules.threadpool.SKRunnable;
import com.skstructure.utils.SKAppUtil;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @创建人 weishukai
 * @创建时间 2018/2/28 16:43
 * @类描述 一句话描述 你的类
 */

public class SKMethod<T> {
    private static final int METHOD_INVOKE = 0;
    private static final int METHOD_INVOKE_BACKGROUND_HTTP = 1;
    private static final int METHOD_INVOKE_BACKGROUND_WORK = 2;
    private static final int METHOD_INVOKE_BACKGROUND_SINGLEWORK = 3;
    private static final int METHOD_INVOKE_DISPLAY = 4;
    private final Class<T> tClass;
    private final Object impl;
    private final Method method;
    private final boolean isRepeat;
    private final int interceptorValue;
    private final int backgroundType;
    /**
     * 是否正在执行
     */
    private boolean isPerform = false;

    public SKMethod(Class<T> tClass, Object impl, Method method, boolean isRepeat, int interceptorValue, int backgroundType) {
        this.tClass = tClass;
        this.impl = impl;
        this.method = method;
        this.isRepeat = isRepeat;
        this.interceptorValue = interceptorValue;
        this.backgroundType = backgroundType;
    }


    static <T> SKMethod createPreMethod(Class<T> tClass, Object impl, Method method) {
        /**
         * 解析是否重复
         */
        boolean isRepeat = parseRepeat(method);
        /**
         * 解析拦截器的值
         */
        int interceptorValue = parseInterceptor(method);
        /**
         * 解析后台执行类型
         */
        int backgroundType = parseBackground(method);

        return new SKMethod(tClass, impl, method, isRepeat, interceptorValue, backgroundType);

    }

    static <T> SKMethod createDisplayMethod(Class<T> tClass, Object impl, Method method) {
        /**
         * 解析是否重复
         */
        boolean isRepeat = parseRepeat(method);
        /**
         * 解析拦截器的值
         */
        int interceptorValue = parseInterceptor(method);
        /**
         * 解析后台执行类型
         */
        int backgroundType = METHOD_INVOKE_DISPLAY;

        return new SKMethod(tClass, impl, method, isRepeat, interceptorValue, backgroundType);

    }

    private static boolean parseRepeat(Method method) {
        Repeat repeat = method.getAnnotation(Repeat.class);
        if (repeat != null) {
            return repeat.value();
        }
        return false;
    }

    private static int parseInterceptor(Method method) {
        Interceptor interceptor = method.getAnnotation(Interceptor.class);
        if (interceptor != null) {
            return interceptor.value();
        }
        return -1;
    }

    private static int parseBackground(Method method) {
        Background background = method.getAnnotation(Background.class);
        if (background != null) {
            switch (background.value()) {
                case HTTP:
                    return METHOD_INVOKE_BACKGROUND_HTTP;
                case WORK:
                    return METHOD_INVOKE_BACKGROUND_WORK;
                case SINGLEWORK:
                    return METHOD_INVOKE_BACKGROUND_SINGLEWORK;
            }
        }
        return METHOD_INVOKE;
    }

    public <R> R invoke(Object[] objects) {
        R result = null;
        if (!isRepeat) {//不能重复，又在执行
            if (isPerform) {
                Log.e("tag", "该方法正在执行");
//                if (SKHelper.getSkApplication().isLogOpen()) {
//                    StringBuilder stringBuilder = new StringBuilder();
//                    stringBuilder.append(impl.getClass().getSimpleName());
//                    stringBuilder.append(".");
//                    stringBuilder.append(method.getName());
//                    L.i("该方法正在执行 - %s", stringBuilder.toString());
//                }
                return null;
            }
            isPerform = true;
        }
        switch (backgroundType) {
            case METHOD_INVOKE:
                result = invokePreMethod(objects);
                break;
            case METHOD_INVOKE_DISPLAY:
                result = invokeDisplayMethod(objects);
                break;
            default:
                MethodRunnable methodRunnable = new MethodRunnable();
                methodRunnable.setOutClass(this);
                methodRunnable.setArgs(objects);
                switch (backgroundType) {
                    case METHOD_INVOKE_BACKGROUND_HTTP:
                        SKHelper.getSkHttpExecutor().execute(methodRunnable);
                        break;
                    case METHOD_INVOKE_BACKGROUND_WORK:
                        SKHelper.getSkWorkExecutor().execute(methodRunnable);
                        break;
                    case METHOD_INVOKE_BACKGROUND_SINGLEWORK:
                        SKHelper.getSkSingleWorkExecutor().execute(methodRunnable);
                        break;
                }
                break;
        }
        return result;
    }

    <R> R invokePreMethod(Object[] objects) {
        R result = null;
        try {
            result = exePreMethod(method, impl, objects);
        } catch (Exception e) {
            exeError(method, e);
        } finally {
            isPerform = false;
        }
        return result;
    }

    private <R> R invokeDisplayMethod(Object[] objects) {
        R result = null;
        try {
            result = exeDisplayMethod(method, impl, objects);
        } catch (Exception e) {
            exeError(method, e);
        } finally {
            isPerform = false;
        }
        return result;
    }

    private <R> R exeDefauleMethod(Method method, Object impl, Object[] objects) throws IllegalAccessException, InvocationTargetException {
        return (R) method.invoke(impl, objects);
    }

    private <R> R exePreMethod(Method method, Object impl, Object[] objects) throws IllegalAccessException, InvocationTargetException {
        boolean isInterceptor = false;
        if (interceptorValue != -1 && SKHelper.getSkMethodProxy().preMethodStartInterceptor != null) {
            isInterceptor = SKHelper.getSkMethodProxy().preMethodStartInterceptor.interceptor(tClass, impl.getClass().getName(), method.getName(), objects, interceptorValue);
        }
        if (isInterceptor) {
            if (SKHelper.getSkApplication().isLogOpen()) {
                L.i(method.getName() + "方法被过滤");
            }
            return null;
        } else {
            R result = exeDefauleMethod(method, impl, objects);
            if (interceptorValue != -1 && SKHelper.getSkMethodProxy().preMethodEndInterceptor != null) {
                SKHelper.getSkMethodProxy().preMethodEndInterceptor.interceptor(tClass, impl.getClass().getName(), method.getName(), objects, interceptorValue);
            }
            return result;
        }
    }

    private <R> R exeDisplayMethod(final Method method, final Object impl, final Object[] objects) throws IllegalAccessException, InvocationTargetException {
        boolean isInterceptor = false;
        if (interceptorValue != -1 && SKHelper.getSkMethodProxy().displayMethodStartInterceptor != null) {
            isInterceptor = SKHelper.getSkMethodProxy().displayMethodStartInterceptor.interceptor(tClass, impl.getClass().getName(), method.getName(), objects, interceptorValue);
        }
        if (isInterceptor) {
            if (SKHelper.getSkApplication().isLogOpen()) {
                L.i(method.getName() + "方法被过滤");
            }
            return null;
        } else {
            /**
             * 如果是在主线程，直接执行
             */
            if (SKAppUtil.isMainThread()) {
                if (impl != null) {
                    R result = exeDefauleMethod(method, impl, objects);
                    if (interceptorValue != -1 && SKHelper.getSkMethodProxy().displayMethodEndInterceptor != null) {
                        SKHelper.getSkMethodProxy().displayMethodEndInterceptor.interceptor(tClass, impl.getClass().getName(), method.getName(), objects, interceptorValue);
                    }
                    return result;
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
                            method.invoke(impl, objects);
                            if (interceptorValue != -1 && SKHelper.getSkMethodProxy().displayMethodEndInterceptor != null) {
                                SKHelper.getSkMethodProxy().displayMethodEndInterceptor.interceptor(tClass, impl.getClass().getName(), method.getName(), objects, interceptorValue);
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            };

            SKHelper.getSkMainExecutor().execute(runnable);
        }
        return null;
    }

    public void exeError(Method method, Exception e) {
        if (SKHelper.getSkApplication().isLogOpen()) {
            e.printStackTrace();
        }
    }
}

class MethodRunnable extends SKRunnable {
    Object[] objects;
    private WeakReference<SKMethod> weakReference;

    public MethodRunnable() {
        super("MethodRunnable");
    }

    public void setOutClass(SKMethod skMethod) {
        weakReference = new WeakReference<>(skMethod);
    }

    public void setArgs(Object[] objects) {
        this.objects = objects;
    }

    @Override
    protected void execute() {
        if (weakReference != null) {
            SKMethod skMethod = weakReference.get();
            if (skMethod != null) {
                skMethod.invokePreMethod(objects);
            }
        }
    }
}
