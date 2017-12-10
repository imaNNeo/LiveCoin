package com.base.baseproject.mvp.activity.intro;

import com.base.baseproject.data.preferences.PreferencesHelper;
import com.base.baseproject.mvp.base.BaseMvpPresenter;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class IntroPresenter extends BaseMvpPresenter<IntroView>{

    PreferencesHelper mPrefHelper;
    public IntroPresenter(PreferencesHelper preferencesHelper){
        mPrefHelper = preferencesHelper;
    }

    public void onStepsFinished() {
        mPrefHelper.set(PreferencesHelper.KEY_IS_INTRO_PASSED,String.valueOf(true));
        getMvpView().openEnterActivity();
    }
}