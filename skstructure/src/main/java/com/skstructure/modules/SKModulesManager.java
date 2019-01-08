package com.skstructure.modules;

import com.skstructure.SKApplication;
import com.skstructure.modules.methodproxy.SKMethodProxy;
import com.skstructure.modules.retrofit2.Retrofit;
import com.skstructure.modules.screen.SKScreenManager;
import com.skstructure.modules.structure.SKStructureManager;
import com.skstructure.modules.threadpool.SKExecutorManager;
import com.skstructure.modules.toast.SKToast;

/**
 * @创建人 weishukai
 * @创建时间 2018/2/27 15:37
 * @类描述 一句话描述 你的类
 */

public class SKModulesManager {
    /**
     * application对象
     */
    private final SKApplication skApplication;
    /**
     * 管理代理的
     */
    private SKMethodProxy skMethodProxy;
    /**
     * 管理线程池
     */
    private final SKExecutorManager skExecutorManager;
    /**
     * 管理StructureModel
     */
    private final SKStructureManager skStructureManager;
    private final SKToast skToast;
    /**
     * 管理activity
     */
    private final SKScreenManager skScreenManager;
    private Retrofit retrofit;


    public SKModulesManager(SKApplication skApplication) {
        this.skApplication = skApplication;
        skExecutorManager = new SKExecutorManager();
        skStructureManager = new SKStructureManager();
        skToast = new SKToast();
        skScreenManager = new SKScreenManager();
    }

    public void initSKMethodProxy(SKMethodProxy methodInterceptor) {
        skMethodProxy = methodInterceptor;
    }

    public void initRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public SKApplication getSkApplication() {
        return skApplication;
    }

    public SKMethodProxy getSkMethodProxy() {
        return skMethodProxy;
    }

    public SKExecutorManager getSkExecutorManager() {
        return skExecutorManager;
    }

    public SKStructureManager getSkStructureManager() {
        return skStructureManager;
    }

    public SKToast getSkToast() {
        return skToast;
    }

    public SKScreenManager getSkScreenManager() {
        return skScreenManager;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
