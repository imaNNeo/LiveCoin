package com.base.baseproject.mvp;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.base.baseproject.di.component.ApplicationComponent;
import com.base.baseproject.di.component.DaggerApplicationComponent;
import com.base.baseproject.di.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class MyApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent =
                DaggerApplicationComponent.builder()
                        .applicationModule(new ApplicationModule(this))
                        .build();
        mApplicationComponent.inject(this);


        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        if(!LeakCanary.isInAnalyzerProcess(this)){
            LeakCanary.install(this);
        }
    }

    public ApplicationComponent getComponent(){
        return mApplicationComponent;
    }
}
