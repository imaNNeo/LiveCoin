package com.base.baseproject.mvp.fragment.pager.page3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.base.baseproject.R;
import com.base.baseproject.listeners.OnActionClickedListener;
import com.base.baseproject.mvp.base.BaseFragment;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class Page3Fragment extends BaseFragment implements Page3View {

    public static Page3Fragment getInstance() {
        return new Page3Fragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_page3;
    }

    @Override
    public void showSystemError(String message, OnActionClickedListener listener) {
        super.showSystemErrorOverlay(message,listener);
    }

    @Override
    public void hideSystemError() {
        super.hideSystemErrorOverlay();
    }

    @Override
    public void showInternetRetry(OnActionClickedListener listener) {
        super.showInternetRetryOverlay(listener);
    }

    @Override
    public void hideInternetRetry() {
        super.hideInternetRetryOverlay();
    }

}
