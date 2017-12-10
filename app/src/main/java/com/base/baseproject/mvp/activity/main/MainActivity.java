package com.base.baseproject.mvp.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.base.baseproject.R;
import com.base.baseproject.di.annotation.ActivityQualifier;
import com.base.baseproject.mvp.base.BaseActivity;
import com.base.baseproject.mvp.fragment.FragmentsHandler;
import com.base.baseproject.viewhelper.adapter.AdapterSampleItems;

import javax.inject.Inject;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class MainActivity extends BaseActivity implements MainView {

    public static void start(Context ctx){
        ctx.startActivity(new Intent(ctx, MainActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @ActivityQualifier
    @Inject
    FragmentsHandler mFragsHandler;

    @Inject
    AdapterSampleItems mAdapter;

    @Inject
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);

        mPresenter.onAttach(this);

        Log.d("SS",mPresenter.toString());

        mFragsHandler.setFragsContainerResId(R.id.fl_container);
        mFragsHandler.openMainFragment();

        init();

    }

    private void init() {
        initToolbar();
    }

    private void initToolbar() {
        mToolbarHolder.init(this);
        mToolbarHolder.ivBack.setVisibility(View.GONE);
        mToolbarHolder.tvTitle.setText(getString(R.string.app_name));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }


}