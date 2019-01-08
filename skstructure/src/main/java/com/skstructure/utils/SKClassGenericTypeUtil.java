package com.skstructure.utils;

import com.skstructure.core.Impl;
import com.skstructure.core.SKIPre;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @创建人 weishukai
 * @创建时间 2018/2/27 15:29
 * @类描述 一句话描述 你的类
 */

public class SKClassGenericTypeUtil {

    /**
     * 获取当前类填充的泛型
     * 用于ui中，如activity、fragment，因为父类已指定泛型继承SKIPre
     *
     * @param clazz
     * @param index
     * @return
     */
    public static Class getClassGenericTypeSure(Class clazz, int index) {
        Type type = clazz.getGenericSuperclass();//获取直接父类，包含泛型
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] types = parameterizedType.getActualTypeArguments();
            if (types.length >= 1) {
                return (Class) types[index];
            }
        }
        return null;
    }

    /**
     * 因为在Pre中获取ui时，ui的不确定性，故没有做泛型限制，可能实际编码中也添加了泛型，故获取方法不一样
     *
     * @param clazz
     * @param index
     * @return
     */
    public static Class getClassGenericTypeNotSure(Class clazz, int index) {
        Class classType = getClassGenericTypeSure(clazz, index);
        if (classType != null) {
            return classType;
        }
        Type[] types = clazz.getGenericInterfaces();
        if (types != null && types.length >= 1) {
            if (types[index] instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) types[0];
                Type[] typesParams = parameterizedType.getActualTypeArguments();
                if (typesParams.length >= 1) {
                    return (Class) types[index];
                }
            }
        }
        return Object.class;
    }

    /**
     * 获取接口aClass的注解Impl实现类
     *
     * @param aClass
     * @param <A>
     * @return
     */
    public static <A> Object getImplAniClass(Class<A> aClass) {
        SKCheckUtil.checkIsInterface(aClass);
        Impl impl = aClass.getAnnotation(Impl.class);
        SKCheckUtil.checkNotNull(impl, "该接口没有指定Impl实现类");
        try {
            Class implClass = Class.forName(impl.value().getName());
            SKCheckUtil.checkNotNull(implClass, "实现类为空");
            Constructor implClassCons = implClass.getDeclaredConstructor();
            implClassCons.setAccessible(true);
            Object o = implClassCons.newInstance();
            return o;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
