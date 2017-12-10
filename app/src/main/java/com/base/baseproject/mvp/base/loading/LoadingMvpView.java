package com.base.baseproject.mvp.base.loading;

import com.base.baseproject.listeners.OnActionClickedListener;
import com.base.baseproject.mvp.base.BaseMvpView;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public interface LoadingMvpView extends BaseMvpView {
    void showSystemError(String message, OnActionClickedListener listener);
    void hideSystemError();
    void showInternetRetry(OnActionClickedListener listener);
    void hideInternetRetry();
}
