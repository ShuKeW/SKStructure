package com.skstructure.modules.structure;

import com.skstructure.SKHelper;
import com.skstructure.core.Impl;
import com.skstructure.core.SKIPre;
import com.skstructure.core.SKPre;
import com.skstructure.modules.methodproxy.SKProxy;
import com.skstructure.utils.SKClassGenericTypeUtil;
import com.skstructure.utils.SKCheckUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

/**
 * @创建人 weishukai
 * @创建时间 2018/2/27 15:38
 * @类描述 一句话描述 你的类
 */

public class SKStructureModel {
    public final int key;
    private Object ui;
    private Class IPre;
    private SKProxy skProxy;
    private Stack<Class> superClass;


    public SKStructureModel(Object ui) {
        key = ui.hashCode();
        this.ui = ui;
        /**
         * 获取泛型
         */
        IPre = SKClassGenericTypeUtil.getClassGenericTypeSure(ui.getClass(), 0);
        SKCheckUtil.checkNotNull(IPre, "获取不到泛型");
        SKCheckUtil.checkIsInterface(IPre);

        /**
         * 获取实现类
         */
        Object impl = SKClassGenericTypeUtil.getImplAniClass(IPre);
        if (impl instanceof SKIPre) {
            ((SKIPre) impl).create(this);
        }
        skProxy = SKHelper.getSkMethodProxy().createPreProxy(IPre, impl);

        superClass = new Stack<>();
        Class tmpClass = impl.getClass().getSuperclass();
        if (tmpClass != null) {
            while (!tmpClass.equals(SKPre.class)) {//在继承SKPre之前自己的多层继承
                if (tmpClass.getInterfaces() != null) {
                    Class clazz = tmpClass.getInterfaces()[0];
                    superClass.add(clazz);
                }
                tmpClass = tmpClass.getSuperclass();
            }
        }

    }


    public Class getIPre() {
        return IPre;
    }

    public Object getUi() {
        return ui;
    }

    public SKProxy getSkProxy() {
        return skProxy;
    }

    public boolean isSupterClass(Class clazz) {
        return superClass.search(clazz) != -1;
    }

    public void destory() {

    }
}
