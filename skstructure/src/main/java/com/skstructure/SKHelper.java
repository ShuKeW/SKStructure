package com.skstructure;

import com.skstructure.core.SKIPre;
import com.skstructure.modules.SKModulesManager;
import com.skstructure.modules.display.SKIDisplsy;
import com.skstructure.modules.methodproxy.SKMethodProxy;
import com.skstructure.modules.retrofit2.Retrofit;
import com.skstructure.modules.screen.SKScreenManager;
import com.skstructure.modules.structure.SKStructureManager;
import com.skstructure.modules.threadpool.SKHttpExecutor;
import com.skstructure.modules.threadpool.SKMainExecutor;
import com.skstructure.modules.threadpool.SKSingleWorkExecutor;
import com.skstructure.modules.threadpool.SKWorkExecutor;
import com.skstructure.modules.toast.SKToast;

/**
 * @创建人 weishukai
 * @创建时间 2018/2/28 16:22
 * @类描述 一句话描述 你的类
 */

public class SKHelper {
    private static SKModulesManager mSkModulesManager;

    protected <M extends SKModulesManager> M getModulesManager() {
        return (M) mSkModulesManager;
    }

    public static void with(SKModulesManager skModulesManager) {
        mSkModulesManager = skModulesManager;
    }

    public static SKMethodProxy getSkMethodProxy() {
        return mSkModulesManager.getSkMethodProxy();
    }

    public static SKApplication getSkApplication() {
        return mSkModulesManager.getSkApplication();
    }

    public static SKHttpExecutor getSkHttpExecutor() {
        return mSkModulesManager.getSkExecutorManager().getSkHttpExecutor();
    }

    public static SKWorkExecutor getSkWorkExecutor() {
        return mSkModulesManager.getSkExecutorManager().getSkWorkExecutor();
    }

    public static SKSingleWorkExecutor getSkSingleWorkExecutor() {
        return mSkModulesManager.getSkExecutorManager().getSkSingleWorkExecutor();
    }

    public static SKStructureManager getSkStructureManager() {
        return mSkModulesManager.getSkStructureManager();
    }

    public static SKMainExecutor getSkMainExecutor() {
        return mSkModulesManager.getSkExecutorManager().getSkMainExecutor();
    }

    public static SKToast getSkToast() {
        return mSkModulesManager.getSkToast();
    }

    public static SKScreenManager getSkScreenManager() {
        return mSkModulesManager.getSkScreenManager();
    }

    public static Retrofit getRetrofit() {
        return mSkModulesManager.getRetrofit();
    }

    public static <D extends SKIDisplsy> D display(Class<D> dClass) {
        return mSkModulesManager.getSkStructureManager().getDisplay(dClass);
    }

    public static <P extends SKIPre> P pre(Class<P> pClass, int index) {
        return mSkModulesManager.getSkStructureManager().getPre(pClass, index);
    }

    public static <H> H http(Class<H> hClass) {
        return mSkModulesManager.getSkStructureManager().getHttp(hClass);
    }
    public static <N> N nullProxy(Class<N> nClass) {
        return mSkModulesManager.getSkStructureManager().getNullProxy(nClass);
    }

}
