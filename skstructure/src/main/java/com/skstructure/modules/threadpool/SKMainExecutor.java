package com.skstructure.modules.threadpool;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/2 10:27
 * @类描述 一句话描述 你的类
 */

public class SKMainExecutor implements Executor {
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(Runnable command) {
        handler.post(command);
    }
}
