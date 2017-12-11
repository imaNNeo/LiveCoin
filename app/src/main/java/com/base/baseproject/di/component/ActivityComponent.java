package com.base.baseproject.di.component;

import android.content.Context;

import com.base.baseproject.data.api.ApiHelper;
import com.base.baseproject.data.db.DbHelper;
import com.base.baseproject.data.preferences.PreferencesHelper;
import com.base.baseproject.data.user.UserHelper;
import com.base.baseproject.di.annotation.ActivityContext;
import com.base.baseproject.di.annotation.PerActivity;
import com.base.baseproject.di.module.ActivityModule;
import com.base.baseproject.mvp.activity.main.MainActivity;
import com.base.baseproject.mvp.activity.splash.SplashActivity;
import com.base.baseproject.rx.SchedulerProvider;
import com.base.baseproject.viewhelper.adapter.AdapterCoins;
import com.base.baseproject.viewhelper.dialog.DialogSelectItem;

import dagger.Component;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(SplashActivity activity);
    void inject(DialogSelectItem fragment);


    UserHelper userHelper();
    PreferencesHelper preferencesHelper();
    ApiHelper apiHelper();
    DbHelper dbHelper();
    SchedulerProvider schedulerProvider();
    AdapterCoins adapterCoins();

    @ActivityContext
    Context getActivityContext();

}