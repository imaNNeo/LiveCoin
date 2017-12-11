package com.base.baseproject.mvp.activity.splash;

import com.base.baseproject.data.preferences.PreferencesHelper;
import com.base.baseproject.data.user.UserHelper;
import com.base.baseproject.mvp.base.BaseMvpPresenter;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class SplashPresenter extends BaseMvpPresenter<SplashView> {

    UserHelper mUserHelper;
    PreferencesHelper mPreferencesHelper;
    public SplashPresenter(UserHelper userHelper, PreferencesHelper preferencesHelper) {
        super();
        mUserHelper = userHelper;
        mPreferencesHelper = preferencesHelper;
    }

    public void allPermissionsGranted(){
        getMvpView().showLoading();
    }

    public void onWaitingTimeFinished(){
        tryGotoNextPage();
    }


    private void tryGotoNextPage(){
        if(!isViewAttached())return;
        getMvpView().closeYourself();

        getMvpView().gotoMain();
    }
}
