package com.skstructure.utils;

/**
 * @创建人 weishukai
 * @创建时间 2018/2/28 11:34
 * @类描述 一句话描述 你的类
 */

public class SKCheckUtil {

    public static <T> boolean checkNotNull(T reference, boolean isException) {
        if (reference == null) {
            if (isException) {
                throw new NullPointerException();
            } else {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean checkNotNull(T reference, String errorMessageTemplate) {
        if (reference == null) {
            throw new NullPointerException(errorMessageTemplate);
        }
        return true;
    }

    public static <T> boolean checkIsInterface(Class<T> clazz) {
        if (checkNotNull(clazz, true) && clazz.isInterface()) {
            return true;
        } else {
            throw new IllegalArgumentException(clazz.toString() + "不是接口");
        }
    }
}
