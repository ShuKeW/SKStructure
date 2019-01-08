package com.skstructure.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @创建人 weishukai
 * @创建时间 17/1/6 下午7:07
 * @类描述 一句话说明这个类是干什么的
 */

public abstract class RVHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected RecyclerView recyclerView;
    protected T dataBean;

    public RVHolder(View itemView) {
        super(itemView);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }


    public void bindData(T model, int position){
        this.dataBean = model;
        onBindData(model,position);
    }

    public abstract void onBindData(T model, int position);

    @Override
    public void onClick(View v) {

    }

    /**
     * 销毁资源
     */
    public void destory() {
        recyclerView = null;
        onDestory();
    }

    public abstract void onDestory();

}
