package com.skstructure.modules.threadpool;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/1 10:24
 * @类描述 一句话描述 你的类
 */

public class SKExecutorManager {
    private SKHttpExecutor skHttpExecutor;
    private SKWorkExecutor skWorkExecutor;
    private SKSingleWorkExecutor skSingleWorkExecutor;
    private SKMainExecutor skMainExecutor;

    public SKExecutorManager() {
        skMainExecutor = new SKMainExecutor();
    }

    public synchronized SKHttpExecutor getSkHttpExecutor() {
        if (skHttpExecutor == null) {
            skHttpExecutor = new SKHttpExecutor();
        }
        return skHttpExecutor;
    }

    public synchronized SKWorkExecutor getSkWorkExecutor() {
        if (skWorkExecutor == null) {
            skWorkExecutor = new SKWorkExecutor();
        }
        return skWorkExecutor;
    }

    public synchronized SKSingleWorkExecutor getSkSingleWorkExecutor() {
        if (skSingleWorkExecutor == null) {
            skSingleWorkExecutor = new SKSingleWorkExecutor();
        }
        return skSingleWorkExecutor;
    }

    public SKMainExecutor getSkMainExecutor() {
        return skMainExecutor;
    }
}
