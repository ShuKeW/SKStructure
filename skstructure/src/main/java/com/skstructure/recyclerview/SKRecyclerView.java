package com.skstructure.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @创建人 weishukai
 * @创建时间 16/12/9 下午5:46
 * @类描述 自定义RecyclerView
 */
public class SKRecyclerView extends RecyclerView {

    private static final String TAG = "SKRecyclerView";

    private boolean isLoadMoreComplete = true;

    private OnLoadMoreListener onLoadMoreListener;

    /**
     * 加载更多
     */
    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public SKRecyclerView(Context context) {
        super(context);
    }

    public SKRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SKRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        Log.d(TAG, "onScrollStateChanged");
        if (state == SCROLL_STATE_IDLE) {
            checkIsLoadMore();
        }
    }

    /**
     * 判断是否要
     */
    protected void checkIsLoadMore() {
        if (onLoadMoreListener != null) {
            LayoutManager layoutManager = getLayoutManager();
            if (layoutManager != null) {
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    Log.d(TAG, "checkIsLoadMore:" + linearLayoutManager.findLastVisibleItemPosition() + "  " + linearLayoutManager.getItemCount());
                    if (linearLayoutManager.findLastVisibleItemPosition() == (linearLayoutManager.getItemCount() - 1) && isLoadMoreComplete && onLoadMoreListener != null) {
                        isLoadMoreComplete = false;
                        onLoadMoreListener.onLoadMore();
                    }
                } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                    // TODO: 17/1/4
                }
            }
        }

    }

    /**
     * 设置加载更多监听
     *
     * @param onLoadMoreListener
     */
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    /**
     * 当加载更多完成的时候调用
     */
    public void setLoadMoreComplete() {
        isLoadMoreComplete = true;
    }

    protected boolean isLoadMoreComplete() {
        return isLoadMoreComplete;
    }


    /**
     * 退出时销毁
     */
    public void destory() {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            RecyclerView.ViewHolder viewHolder = getChildViewHolder(child);
            if (viewHolder != null && viewHolder instanceof RVHolder) {
                RVHolder baseViewHolder = (RVHolder) viewHolder;
                baseViewHolder.destory();
            }
            child = null;
        }
        onLoadMoreListener = null;
    }
}
