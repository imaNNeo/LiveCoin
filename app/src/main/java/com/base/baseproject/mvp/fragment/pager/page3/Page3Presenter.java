package com.base.baseproject.mvp.fragment.pager.page3;

import com.base.baseproject.mvp.base.loading.LoadingMvpPresenter;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class Page3Presenter extends LoadingMvpPresenter<Page3View> {

    public Page3Presenter() {
        super();
    }


    @Override
    protected void onRetryClicked(int rqCode) {

    }

    @Override
    public void onInternetAvailable() {
        super.onInternetAvailable();
    }
}
