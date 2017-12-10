package com.base.baseproject.di.component;

import com.base.baseproject.di.annotation.PerFragment;
import com.base.baseproject.di.module.FragmentModule;
import com.base.baseproject.mvp.fragment.login.LoginFragment;
import com.base.baseproject.mvp.fragment.main.MainFragment;
import com.base.baseproject.mvp.fragment.pager.PagerFragment;

import dagger.Component;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
@PerFragment
@Component(dependencies = {ActivityComponent.class},modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(LoginFragment frag);
    void inject(PagerFragment frag);
    void inject(MainFragment frag);
}