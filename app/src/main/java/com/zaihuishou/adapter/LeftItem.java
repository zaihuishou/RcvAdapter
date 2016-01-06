package com.zaihuishou.adapter;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.zaihuishou.rcvadapter.AdapterItem;

/**
 * Created by zhiqiang on 16-1-6.
 */
public class LeftItem extends AdapterItem<Model> implements View.OnClickListener {
    private TextView mName;
    private Model mModel;

    @Override
    protected int getLayoutResId() {
        return R.layout.item_left;
    }

    @Override
    protected void onSetViews() {

    }

    @Override
    protected void onBindViews(View mRootView) {
        super.onBindViews(mRootView);
        mRootView.setOnClickListener(this);
        mName = (TextView) mRootView.findViewById(R.id.name);
    }

    @Override
    protected void onUpdateViews(Model model, int position) {
        if (model != null) {
            mModel = model;
            mName.setText(model.getName());
        }
    }

    @Override
    public void onClick(View v) {
        Snackbar.make(getItemView(), mModel.getName(), Snackbar.LENGTH_SHORT).show();
    }
}
