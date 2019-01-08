package com.skstructure.modules.threadpool;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/1 10:01
 * @类描述 一句话描述 你的类
 */

public abstract class SKRunnable implements Runnable {
    protected final String name;

    public SKRunnable(String format, Object... args) {
        this.name = String.format(format, args);
    }

    @Override
    public void run() {
        // 获取当前线程名称
        String oldName = Thread.currentThread().getName();
        // 设置新名称
        Thread.currentThread().setName(name);
        try {
            // 执行
            execute();
        } finally {
            // 还原名称
            Thread.currentThread().setName(oldName);
        }
    }

    protected abstract void execute();
}
