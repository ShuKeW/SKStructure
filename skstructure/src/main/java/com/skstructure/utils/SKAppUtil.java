package com.skstructure.utils;

import android.os.Looper;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/2 10:19
 * @类描述 一句话描述 你的类
 */

public class SKAppUtil {

    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
