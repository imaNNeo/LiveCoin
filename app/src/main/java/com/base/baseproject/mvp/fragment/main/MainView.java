package com.base.baseproject.mvp.fragment.main;

import com.base.baseproject.data.api.retrofit.models.ResponseObjects;
import com.base.baseproject.mvp.base.loading.LoadingMvpView;

import java.util.List;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public interface MainView extends LoadingMvpView {
    void showLoading();
    void hideLoading();
    void refreshCoinItems(List<ResponseObjects.CoinItem> coinItems);
}
