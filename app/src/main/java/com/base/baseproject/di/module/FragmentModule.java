package com.base.baseproject.di.module;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.base.baseproject.data.api.ApiHelper;
import com.base.baseproject.data.user.UserHelper;
import com.base.baseproject.di.annotation.ActivityContext;
import com.base.baseproject.di.annotation.FragmentQualifier;
import com.base.baseproject.di.annotation.PerFragment;
import com.base.baseproject.mvp.fragment.FragmentsHandler;
import com.base.baseproject.mvp.fragment.login.LoginPresenter;
import com.base.baseproject.mvp.fragment.main.MainPresenter;
import com.base.baseproject.rx.SchedulerProvider;
import com.base.baseproject.viewhelper.adapter.AdapterFragPager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
@Module
public class FragmentModule {
    Fragment mFragment;
    public FragmentModule(Fragment fragment){
        this.mFragment = fragment;
    }

    @Provides
    AdapterFragPager provideAdapterFragPager(@ActivityContext Context ctx){
        return new AdapterFragPager(ctx,mFragment.getChildFragmentManager());
    }

    @FragmentQualifier
    @Provides
    FragmentsHandler provideFragmentsHandler(){
        return new FragmentsHandler(mFragment.getChildFragmentManager());
    }


    @Provides
    @PerFragment
    LoginPresenter provideLoginPresenter(ApiHelper apiHelper, UserHelper userHelper, SchedulerProvider schedulerProvider){
        return new LoginPresenter(apiHelper,userHelper,schedulerProvider);
    }

    @Provides
    @PerFragment
    MainPresenter provideMainPresenter(){
        return new MainPresenter();
    }
}
