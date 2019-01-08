package com.skstructure.modules.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/1 10:15
 * @类描述 一句话描述 你的类
 */

public class SKHttpExecutor extends ThreadPoolExecutor {
    SKHttpExecutor() {
        super(5, 5, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
    }
}
