package com.base.baseproject.di.module;

import android.app.Application;
import android.content.Context;

import com.base.baseproject.data.api.ApiHelper;
import com.base.baseproject.data.api.AppApiHelper;
import com.base.baseproject.data.db.AppDbHelper;
import com.base.baseproject.data.db.DbHelper;
import com.base.baseproject.data.preferences.AppPreferencesHelper;
import com.base.baseproject.data.preferences.PreferencesHelper;
import com.base.baseproject.data.user.AppUserHelper;
import com.base.baseproject.data.user.UserHandler;
import com.base.baseproject.data.user.UserHelper;
import com.base.baseproject.di.annotation.ApplicationContext;
import com.base.baseproject.di.annotation.PerApplication;
import com.base.baseproject.mvp.MyApplication;
import com.base.baseproject.rx.AppSchedulerProvider;
import com.base.baseproject.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
@Module
public class ApplicationModule {

    MyApplication myApplication;
    public ApplicationModule(MyApplication myApplication){
        this.myApplication = myApplication;
    }



    @Provides
    @ApplicationContext
    public Context provideContext(){
        return myApplication;
    }

    @Provides
    @PerApplication
    public Application provideApplication(){
        return myApplication;
    }


    @Provides
    @PerApplication
    DbHelper provideDbHelper(@ApplicationContext Context ctx){
        return new AppDbHelper(ctx);
    }

    @Provides
    @PerApplication
    UserHandler provideUserHandler(PreferencesHelper preferencesHelper){
        return new UserHandler(preferencesHelper);
    }

    @Provides
    @PerApplication
    UserHelper provideUserHelper(@ApplicationContext Context ctx, UserHandler userHandler){
        return new AppUserHelper(ctx,userHandler);
    }

    @Provides
    @PerApplication
    ApiHelper provideApiHelper(@ApplicationContext Context ctx, UserHandler userHandler){
        return new AppApiHelper(ctx,userHandler);
    }

    @Provides
    @PerApplication
    PreferencesHelper providePreferencesHelper(@ApplicationContext Context ctx){
        return new AppPreferencesHelper(ctx);
    }

    @Provides
    @PerApplication
    SchedulerProvider provideSchedulerProvider(){
        return new AppSchedulerProvider();
    }

}
