package com.skstructure.modules.screen;

import com.skstructure.view.SKActivity;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/6 16:01
 * @类描述 一句话描述 你的类
 */

public class SKScreenHolder {
    private SKActivity activity;
    private String activityName;
    private boolean isRunning;
    private boolean isResume;

    public SKScreenHolder(SKActivity fragmentActivity) {
        this.activity = fragmentActivity;
        isRunning = true;
        activityName = fragmentActivity.getClass().getSimpleName();
    }

    public SKActivity getActivity() {
        return activity;
    }

    public void start() {
        isRunning = true;
    }

    public void resume() {
        isResume = true;
    }

    public void pause() {
        isResume = false;
    }

    public void stop() {
        isRunning = false;
    }

    public void result() {
        isRunning = true;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isResume() {
        return isResume;
    }
}
