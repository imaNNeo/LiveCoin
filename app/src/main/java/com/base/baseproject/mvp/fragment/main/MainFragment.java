package com.base.baseproject.mvp.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.base.baseproject.R;
import com.base.baseproject.data.api.retrofit.models.ResponseObjects;
import com.base.baseproject.listeners.OnActionClickedListener;
import com.base.baseproject.mvp.base.BaseFragment;
import com.base.baseproject.viewhelper.adapter.AdapterCoins;
import com.base.baseproject.viewhelper.widget.AppRecyclerView;

import java.util.List;

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
    AdapterCoins mAdapter;


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
        rvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvContent.setAdapter(mAdapter);
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

    @Override
    public void showLoading() {
        super.showPagesLoading();
    }

    @Override
    public void hideLoading() {
        super.hidePagesLoading();
    }

    @Override
    public void refreshCoinItems(List<ResponseObjects.CoinItem> coinItems) {
        mAdapter.resetItems(coinItems);
    }
}
