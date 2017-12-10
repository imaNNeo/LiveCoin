package com.base.baseproject.viewhelper;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.baseproject.R;
import com.base.baseproject.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class ToolbarHolder {

    BaseActivity act;

    public Toolbar mToolbar;

    @BindView(R.id.tv_title)
    public TextView tvTitle;

    @BindView(R.id.iv_back)
    public ImageView ivBack;

    @BindView(R.id.iv_menu)
    public ImageView ivMenu;

    @BindView(R.id.iv_submit)
    public ImageView ivSubmit;


    Unbinder mUnbinder;
    public void init(BaseActivity activity){
        mToolbar = activity.findViewById(R.id.toolbar);

        mUnbinder = ButterKnife.bind(this,mToolbar);
        activity.setSupportActionBar(mToolbar);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mToolbar.setContentInsetsAbsolute(0, 0);
        act = activity;
    }

    @OnClick(R.id.iv_back)
    void onBackClicked(){
        act.onBackPressed();
    }

    public void unbind(){
        if(mUnbinder!=null)
            mUnbinder.unbind();
    }
}