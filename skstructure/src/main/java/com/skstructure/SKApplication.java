package com.skstructure;

import android.app.Application;
import android.content.Context;

import android.util.Log;
import com.skstructure.modules.SKModulesManager;
import com.skstructure.modules.log.L;
import com.skstructure.modules.methodproxy.SKMethodProxy;
import com.skstructure.modules.retrofit2.Retrofit;
import com.skstructure.modules.retrofit2.converter.SKGsonConverterFactory;


/**
 * @创建人 weishukai
 * @创建时间 2018/2/27 11:16
 * @类描述 一句话描述 你的类
 */

public abstract class SKApplication extends Application {
    private SKModulesManager skModulesManager;

    @Override
    public void onCreate() {
        super.onCreate();
        L.plant(new L.Tree() {
            @Override
            protected void log(int priority, String tag, String message, Throwable t) {
                Log.println(priority,tag,message);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        skModulesManager = createModulesManager();
        skModulesManager.initSKMethodProxy(createMethodInterceptor(new SKMethodProxy.Builder()));
        skModulesManager.initRetrofit(createRetrofit(createRetrofitBuilder()));
        initHelper(skModulesManager);
    }

    /**
     * 是否打开日志
     *
     * @return
     */
    public abstract boolean isLogOpen();

    public abstract SKMethodProxy createMethodInterceptor(SKMethodProxy.Builder builder);

    /**
     * 通过重写此方法，创建自己的ModulesManager
     * 方法：继承SKModulesManager，添加项目中用到的服务
     *
     * @return
     */
    public SKModulesManager createModulesManager() {
        return new SKModulesManager(this);
    }

    /**
     * 同样，和createModulesManager配合使用
     *
     * @param skModulesManager
     */
    public void initHelper(SKModulesManager skModulesManager) {
        SKHelper.with(skModulesManager);
    }

    private Retrofit.Builder createRetrofitBuilder() {
        return new Retrofit.Builder()
                .addConverterFactory(SKGsonConverterFactory.create());
    }

    public abstract Retrofit createRetrofit(Retrofit.Builder builder);
}
