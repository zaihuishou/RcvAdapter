package com.zaihuishou.rcvadapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * 用于绑定每个item
 *
 * @param <T>
 */
public abstract class AdapterItem<T> {

    private Context mContext;
    private View mItemView;

    /**
     * @return item布局文件的layoutId
     */
    @LayoutRes
    protected abstract int getLayoutResId();

    /**
     * 初始化views
     * -----------必须实现
     */
    @CallSuper
    protected void onBindViews(final View mRootView) {
        if (mRootView != null) {
            mItemView = mRootView;
            mContext = mRootView.getContext();
        }
    }

    /**
     * 设置view的参数
     */

    protected abstract void onSetViews();

    /**
     * 根据数据来设置item的内部views
     *
     * @param model    数据list内部的model
     * @param position 当前adapter调用item的位置
     */
    protected abstract void onUpdateViews(T model, int position);

    public View getItemView() {
        return mItemView;
    }
}