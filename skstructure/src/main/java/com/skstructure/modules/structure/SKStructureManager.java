package com.skstructure.modules.structure;

import android.support.v4.util.SimpleArrayMap;

import com.skstructure.SKHelper;
import com.skstructure.core.SKIPre;
import com.skstructure.modules.display.SKIDisplsy;
import com.skstructure.modules.methodproxy.SKProxy;
import com.skstructure.utils.SKClassGenericTypeUtil;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/1 16:44
 * @类描述 各种代理的管理类
 */

public class SKStructureManager {

    private final ConcurrentHashMap<Class<?>, SimpleArrayMap<Integer, SKStructureModel>> stackPre;
    private final ConcurrentHashMap<Class<?>, SKProxy> stackDisplay;
    private final ConcurrentHashMap<Class<?>, Object> stackHttp;
    private final ConcurrentHashMap<Class<?>, Object> stackImpl;
    private Object nProxy;

    public SKStructureManager() {
        stackPre = new ConcurrentHashMap<>();
        stackDisplay = new ConcurrentHashMap<>();
        stackHttp = new ConcurrentHashMap<>();
        stackImpl = new ConcurrentHashMap<>();
    }

    public void attach(SKStructureModel skStructureModel) {
            SimpleArrayMap<Integer, SKStructureModel> stack = stackPre.get(skStructureModel.getIPre());
            if (stack == null) {
                stack = new SimpleArrayMap<>();
            }
            stack.put(skStructureModel.key, skStructureModel);
            stackPre.put(skStructureModel.getIPre(), stack);
    }

    public void detach(SKStructureModel skStructureModel) {
            SimpleArrayMap<Integer, SKStructureModel> stack = stackPre.get(skStructureModel.getIPre());
            if (stack != null) {
                SKStructureModel skStructureModelStack = stack.get(skStructureModel.key);
                if (skStructureModelStack != null) {
                    skStructureModelStack.destory();
                    stack.remove(skStructureModelStack);
                    if (stack.size() < 1) {
                        stackPre.remove(stack);
                    }
                }
            }
    }

    public <T extends SKIPre> T getPre(Class<T> tClass, int index) {
        SimpleArrayMap<Integer, SKStructureModel> stack = stackPre.get(tClass);
        if (stack != null && stack.size() > index) {
            SKStructureModel skStructureModel = stack.valueAt(index);
            if (skStructureModel != null && skStructureModel.getSkProxy().proxy != null) {
                return (T) skStructureModel.getSkProxy().proxy;
            }
        }
        return getNullProxy(tClass);
    }

    public <D extends SKIDisplsy> D getDisplay(Class<D> dClass) {
        SKProxy skProxy = stackDisplay.get(dClass);
        if (skProxy == null) {
            Object impl = SKClassGenericTypeUtil.getImplAniClass(dClass);
            skProxy = SKHelper.getSkMethodProxy().createDisplayProxy(dClass, impl);
            stackDisplay.put(dClass, skProxy);
        }
        return (D) skProxy.proxy;
    }

    public <H> H getHttp(Class<H> hClass) {
        Object proxy = stackHttp.get(hClass);
        if (proxy == null) {
            proxy = SKHelper.getRetrofit().create(hClass);
            stackHttp.put(hClass, proxy);
        }
        return (H) proxy;
    }

    public <N> N getNullProxy(Class<N> nClass) {
        synchronized (nProxy){
            if(nProxy == null){
                nProxy = SKHelper.getSkMethodProxy().createNullProxy(nClass);
            }
        }
        return (N) nProxy;
    }
}
