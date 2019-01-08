package com.skstructure.modules.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/1 10:15
 * @类描述 一句话描述 你的类
 */

public class SKSingleWorkExecutor extends ThreadPoolExecutor {
    SKSingleWorkExecutor() {
        super(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }
}
