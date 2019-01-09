package com.skstructure.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.skstructure.SKHelper;
import com.skstructure.core.SKIPre;
import com.skstructure.modules.structure.SKStructureModel;
import com.skstructure.utils.SKClassGenericTypeUtil;

/**
 * @创建人 weishukai
 * @创建时间 2018/2/27 15:34
 * @类描述 activity基类
 */

public abstract class SKActivity<P extends SKIPre> extends AppCompatActivity {
    private SKStructureModel skStructureModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        skStructureModel = new SKStructureModel(this);
        SKHelper.getSkStructureManager().attach(skStructureModel);
        SKHelper.getSkScreenManager().onCreate(this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SKHelper.getSkScreenManager().onStart(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SKHelper.getSkScreenManager().onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SKHelper.getSkScreenManager().onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SKHelper.getSkScreenManager().onStop(this);
    }

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 获取对应的Pre
     *
     * @return
     */
    public P pre() {
        if (skStructureModel == null || skStructureModel.getSkProxy() == null || skStructureModel.getSkProxy().proxy == null) {
            Class iPre = SKClassGenericTypeUtil.getClassGenericTypeNotSure(getClass(), 0);
            return (P) SKHelper.nullProxy(iPre);
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
                return SKHelper.nullProxy(cClass);
            }
            return (C) skStructureModel.getSkProxy().proxy;
        }
        return SKHelper.pre(cClass, index);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SKHelper.getSkScreenManager().onActivityResult(this);
    }

    @Override
    protected void onDestroy() {
        SKHelper.getSkStructureManager().detach(skStructureModel);
        skStructureModel.destory();
        skStructureModel = null;
        SKHelper.getSkScreenManager().onDestroy(this);
        super.onDestroy();
    }
}
