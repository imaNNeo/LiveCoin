package com.base.baseproject.mvp.fragment.login;

import com.base.baseproject.mvp.base.loading.LoadingMvpView;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public interface LoginView extends LoadingMvpView{
    String getEmailText();
    String getPasswordText();
    void onEmailValidateError(int errorValidationCode);
    void onPasswordValidateError(int errorValidationCode);
    void showLoading();
    void hideLoading();
    void showServerMessage(String message);
    void gotoMain();
    void closeEntranceActivities();
}