package com.base.baseproject.viewhelper.dialog;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.base.baseproject.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class DialogWaiting extends AppDialog {
    Unbinder mUnbinder;

    public DialogWaiting(Activity ctx) {
        super(ctx);
        setContentView(R.layout.wait_dialog);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mUnbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_close)
    void onCloseClicked(){
        dismiss();
    }

    @Override
    public View getViewContainer() {
        return findViewById(R.id.root_view);
    }

}