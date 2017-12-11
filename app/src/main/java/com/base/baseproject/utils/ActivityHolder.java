package com.base.baseproject.utils;

import com.base.baseproject.mvp.activity.main.MainActivity;
import com.base.baseproject.mvp.activity.splash.SplashActivity;
import com.base.baseproject.mvp.base.BaseActivity;

import java.lang.ref.WeakReference;

/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class ActivityHolder {
    private static ActivityHolder mActivityHolder;
    public static ActivityHolder getInstance(){
        if(mActivityHolder==null)
            mActivityHolder = new ActivityHolder();

        return mActivityHolder;
    }

    public WeakReference<MainActivity> activityTestLoadingWeakReference;
    public WeakReference<SplashActivity> activitySplashWeakReference;

    public void addActivity(BaseActivity baseActivity){
        if(baseActivity instanceof MainActivity){
            activityTestLoadingWeakReference = new WeakReference<>((MainActivity) baseActivity) ;
        }else if(baseActivity instanceof SplashActivity){
            activitySplashWeakReference = new WeakReference<>((SplashActivity) baseActivity) ;
        }
    }


    public void closeAllActivities() {
        try {activityTestLoadingWeakReference.get().finish();} catch (Exception e) {}
        try {activitySplashWeakReference.get().finish();} catch (Exception e) {}
    }

    public void closeEntranceActivities() {
    }
}