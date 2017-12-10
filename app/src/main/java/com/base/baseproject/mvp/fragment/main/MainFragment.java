package com.base.baseproject.mvp.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.base.baseproject.R;
import com.base.baseproject.listeners.OnActionClickedListener;
import com.base.baseproject.mvp.base.BaseFragment;
import com.base.baseproject.viewhelper.widget.AppRecyclerView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class MainFragment extends BaseFragment implements MainView{
    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }


    @BindView(R.id.rv_content)
    AppRecyclerView rvContent;

    @Inject
    MainPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onAttach(this);
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
