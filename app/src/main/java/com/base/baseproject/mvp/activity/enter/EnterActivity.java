package com.base.baseproject.mvp.activity.enter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.base.baseproject.R;
import com.base.baseproject.di.annotation.ActivityQualifier;
import com.base.baseproject.mvp.base.BaseActivity;
import com.base.baseproject.mvp.fragment.FragmentsHandler;

import javax.inject.Inject;

/**
 * Created by Amir Abdi on 02/05/2017.
 */

public class EnterActivity extends BaseActivity implements EnterView {

    public static void start(Context ctx) {
        ctx.startActivity(new Intent(ctx, EnterActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_enter;
    }


    @ActivityQualifier
    @Inject
    FragmentsHandler mFragsHandler;

    @Inject
    EnterPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getComponent().inject(this);
        super.onCreate(savedInstanceState);

        mPresenter.onAttach(this);

        mFragsHandler.setFragsContainerResId(R.id.fl_container);
        mFragsHandler.openLoginFragment(false);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    private void init() {
        initToolbar();
    }

    private void initToolbar() {
        mToolbarHolder.init(this);
        mToolbarHolder.tvTitle.setText("Enter");
    }

}