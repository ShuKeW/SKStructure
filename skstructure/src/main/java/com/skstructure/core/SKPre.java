package com.skstructure.core;

import com.skstructure.SKHelper;
import com.skstructure.modules.structure.SKStructureModel;
import com.skstructure.utils.SKClassGenericTypeUtil;
import com.skstructure.utils.SKCheckUtil;

/**
 * @创建人 weishukai
 * @创建时间 2018/2/28 14:41
 * @类描述 一句话描述 你的类
 */

public abstract class SKPre<U> implements SKIPre {
    private SKStructureModel skStructureModel;
    private Class<?> uiClass;
    private U uiProxy;

    @Override
    public void create(SKStructureModel skStructureModel) {
        this.skStructureModel = skStructureModel;
        uiClass = SKClassGenericTypeUtil.getClassGenericTypeNotSure(getClass(), 0);
        SKCheckUtil.checkIsInterface(uiClass);
        uiProxy = (U) SKHelper.getSkMethodProxy().createUIProxy(uiClass, skStructureModel.getUi());

        onCreate();
    }

    public abstract void onCreate();
    public abstract void onDestory();

    public U ui() {
        if (uiProxy == null) {
            return (U) SKHelper.nullProxy(uiClass);
        }
        return uiProxy;
    }

    public <T extends SKIPre> T pre(Class<T> tClass) {
        return pre(tClass, 0);
    }

    public <T extends SKIPre> T pre(Class<T> tClass, int index) {
        if (skStructureModel != null && skStructureModel.isSupterClass(tClass)) {
            if (skStructureModel.getSkProxy() != null && skStructureModel.getSkProxy().proxy != null) {
                return (T) skStructureModel.getSkProxy().proxy;
            } else {
                return SKHelper.nullProxy(tClass);
            }
        } else if (skStructureModel != null && skStructureModel.getIPre().equals(tClass)) {
            if (skStructureModel.getSkProxy() != null && skStructureModel.getSkProxy().proxy != null) {
                return (T) skStructureModel.getSkProxy().proxy;
            } else {
                return SKHelper.nullProxy(tClass);
            }
        } else {
            return SKHelper.pre(tClass, index);
        }
    }

    public <H> H http(Class<H> hClass) {
        return SKHelper.http(hClass);
    }

    @Override
    public void destory() {
        onDestory();
        skStructureModel = null;
        uiClass = null;
        uiProxy = null;
    }
}
