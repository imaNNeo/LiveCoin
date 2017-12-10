package com.base.baseproject.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.base.baseproject.data.preferences.PreferencesHelper;
import com.base.baseproject.data.user.UserHelper;
import com.base.baseproject.di.annotation.ActivityContext;
import com.base.baseproject.di.annotation.ActivityQualifier;
import com.base.baseproject.di.annotation.PerActivity;
import com.base.baseproject.mvp.activity.enter.EnterPresenter;
import com.base.baseproject.mvp.activity.intro.IntroPresenter;
import com.base.baseproject.mvp.activity.main.MainPresenter;
import com.base.baseproject.mvp.activity.splash.SplashPresenter;
import com.base.baseproject.mvp.base.BaseActivity;
import com.base.baseproject.mvp.fragment.FragmentsHandler;
import com.base.baseproject.viewhelper.adapter.AdapterCoins;
import com.base.baseproject.viewhelper.adapter.AdapterSampleItems;
import com.base.baseproject.viewhelper.adapter.AdapterStringList;
import com.base.baseproject.viewhelper.adapter.pager.AdapterFragPagerIntro;

import dagger.Module;
import dagger.Provides;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    /*Presenters*/
    @Provides
    @PerActivity
    EnterPresenter provideEnterPresenter(){
        return new EnterPresenter();
    }

    @Provides
    @PerActivity
    MainPresenter provideMainPresenter(){
        return new MainPresenter();
}

    @Provides
    @PerActivity
    SplashPresenter provideSplashPresenter(UserHelper userHelper, PreferencesHelper preferencesHelper){
        return new SplashPresenter(userHelper,preferencesHelper);
    }

    @Provides
    @PerActivity
    IntroPresenter provideIntroPresenter(PreferencesHelper preferencesHelper){
        return new IntroPresenter(preferencesHelper);
    }
    /*Presenters*/


    /*Adapters*/
    @Provides
    AdapterSampleItems provideAdapterSampleItems(){
        return new AdapterSampleItems(mActivity);
    }

    @Provides
    AdapterStringList provideAdapterStringList(){
        return new AdapterStringList(mActivity);
    }

    @Provides
    AdapterCoins provideAdapterCoins(){
        return new AdapterCoins();
    }

    @Provides
    AdapterFragPagerIntro provideAdapterFragPagerIntro(@ActivityContext Context ctx){
        return new AdapterFragPagerIntro(ctx,((BaseActivity)mActivity).getSupportFragmentManager());
    }
    /*Adapters*/

    @ActivityQualifier
    @Provides
    FragmentsHandler provideFragmentHandler(){
        return new FragmentsHandler(((AppCompatActivity)mActivity).getSupportFragmentManager());
    }
}