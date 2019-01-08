package com.skstructure.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.skstructure.SKHelper;
import com.skstructure.core.SKIPre;
import com.skstructure.modules.structure.SKStructureModel;
import com.skstructure.utils.SKClassGenericTypeUtil;

/**
 * @创建人 weishukai
 * @创建时间 2018/3/7 09:47
 * @类描述 一句话描述 你的类
 */

public abstract class SKFragment<P extends SKIPre> extends Fragment {
    private SKStructureModel skStructureModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        skStructureModel = new SKStructureModel(this);
        SKHelper.getSkStructureManager().attach(skStructureModel);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SKHelper.getSkStructureManager().detach(skStructureModel);
    }

    /**
     * 获取对应的Pre
     *
     * @return
     */
    public P pre() {
        if (skStructureModel == null || skStructureModel.getSkProxy() == null || skStructureModel.getSkProxy().proxy == null) {
            Class iPre = SKClassGenericTypeUtil.getClassGenericTypeNotSure(getClass(), 0);
            return (P) SKHelper.getSkMethodProxy().createNullProxy(iPre);
        }
        return (P) skStructureModel.getSkProxy().proxy;
    }

    public <C extends SKIPre> C pre(Class<C> cClass) {
        return pre(cClass, 0);
    }

    /**
     * 获取指定的Pre，用于调用其他打开的页面
     *
     * @param cClass
     * @param index  按照打开的顺序，最后打开为0
     * @param <C>
     * @return
     */
    public <C extends SKIPre> C pre(Class<C> cClass, int index) {
        if (skStructureModel != null && skStructureModel.getIPre().equals(cClass)) {
            if (skStructureModel.getSkProxy() == null || skStructureModel.getSkProxy().proxy == null) {
                return SKHelper.getSkMethodProxy().createNullProxy(cClass);
            }
            return (C) skStructureModel.getSkProxy().proxy;
        }
        return SKHelper.pre(cClass, index);
    }


}
