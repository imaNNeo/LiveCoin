package com.base.baseproject.mvp.activity.splash;


import com.base.baseproject.mvp.base.BaseMvpView;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public interface SplashView extends BaseMvpView{
    void showLoading();
    void hideLoading();
    void closeYourself();
    void gotoMain();
}