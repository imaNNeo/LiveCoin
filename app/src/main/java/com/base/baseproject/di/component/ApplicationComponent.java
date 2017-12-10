package com.base.baseproject.di.component;

import com.base.baseproject.data.api.ApiHelper;
import com.base.baseproject.data.db.DbHelper;
import com.base.baseproject.data.preferences.PreferencesHelper;
import com.base.baseproject.data.user.UserHelper;
import com.base.baseproject.di.annotation.PerApplication;
import com.base.baseproject.di.module.ApplicationModule;
import com.base.baseproject.mvp.MyApplication;
import com.base.baseproject.rx.SchedulerProvider;

import dagger.Component;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
@PerApplication
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MyApplication app);

    UserHelper userHelper();
    PreferencesHelper preferencesHelper();
    ApiHelper apiHelper();
    DbHelper dbHelper();
    SchedulerProvider schedulerProvider();
}