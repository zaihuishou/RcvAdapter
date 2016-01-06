package com.zaihuishou.rcvadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * @param <T>
 */
public abstract class RcvAdapter<T> extends RecyclerView.Adapter {

    private List<T> mDataList;

    private Object mItemType;

    private ItemUtil mUtil = new ItemUtil();

    protected RcvAdapter(List<T> data) {
        mDataList = data;
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public List<T> getDataList() {
        return mDataList;
    }

    /**
     * 可以被复写用于单条刷新等
     */
    public void updateData(@NonNull List<T> data) {
        mDataList = data;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * instead by{@link #getItemViewType(Object)}
     */
    @Deprecated
    @Override
    public int getItemViewType(int position) {
        mItemType = getItemViewType(mDataList.get(position));
        return mUtil.getIntType(mItemType);
    }


    public Object getItemViewType(T t) {
        return -1;
    }

    @NonNull
    public abstract AdapterItem<T> getItemView(Object type);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RcvAdapterItem(parent.getContext(), parent, getItemView(mItemType));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RcvAdapterItem) holder).getItem().onUpdateViews(mDataList.get(position), position);
    }


    private class RcvAdapterItem extends RecyclerView.ViewHolder {

        private AdapterItem<T> mItem;

        protected RcvAdapterItem(Context context, ViewGroup parent, AdapterItem<T> item) {
            super(LayoutInflater.from(context).inflate(item.getLayoutResId(), parent, false));
            mItem = item;
            mItem.onBindViews(itemView);
            mItem.onSetViews();
        }

        protected AdapterItem<T> getItem() {
            return mItem;
        }

    }

}
