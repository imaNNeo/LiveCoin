package com.base.baseproject.mvp.activity.intro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.base.baseproject.R;
import com.base.baseproject.mvp.activity.enter.EnterActivity;
import com.base.baseproject.mvp.base.BaseActivity;
import com.base.baseproject.viewhelper.adapter.pager.AdapterFragPagerIntro;
import com.base.baseproject.viewhelper.viewpageranimator.Property;
import com.base.baseproject.viewhelper.viewpageranimator.Provider;
import com.base.baseproject.viewhelper.viewpageranimator.ViewPagerAnimator;
import com.base.baseproject.viewhelper.widget.AppImageView;
import com.base.baseproject.viewhelper.widget.AppTextView;
import com.base.baseproject.viewhelper.widget.AppViewPager;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class IntroActivity extends BaseActivity implements IntroView{

    public static void start(Context ctx) {
        ctx.startActivity(new Intent(ctx,IntroActivity.class));
    }


    @BindView(R.id.vp_intro)
    AppViewPager vpIntro;

    @Inject
    AdapterFragPagerIntro mAdapterPager;


    /*Footer*/
    @BindView(R.id.tv_footerSkip)
    public AppTextView tvFooterSkip;

    @BindView(R.id.iv_footerNext)
    public AppImageView ivFooterNext;
    /*Footer*/


    @Inject
    IntroPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_intro;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);

        init();

        mPresenter.onAttach(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    private void init() {
        initViews();
    }


    private void initViews() {
        mAdapterPager.setParentViewPager(vpIntro);
        vpIntro.setAdapter(mAdapterPager);
        vpIntro.setOffscreenPageLimit(mAdapterPager.getCount());


        ivFooterNext.setOnClickListener(v -> {
            if(vpIntro.getCurrentItem()==vpIntro.getAdapter().getCount()-1) {
                mPresenter.onStepsFinished();
            }else
                mAdapterPager.gotoNext();
        });

        tvFooterSkip.setOnClickListener(v -> mPresenter.onStepsFinished());



        /*Color Animator*/
        Provider<Integer> colorProvider = position -> {
            Log.d("SS","Pos : " + position);
            return  mAdapterPager.getColour(position);
        };
        Property<Integer> colorProperty = value -> vpIntro.setBackgroundColor(value);
        ViewPagerAnimator.ofArgb(vpIntro,colorProvider,colorProperty);
        /*Color Animator*/

        /*Frag1Melon Animator*/
        Provider<Number> posProvider1 = position -> mAdapterPager.getPage1WelcomePos(position);
        Property<Number> posPeroperty1 = value -> mAdapterPager.setPageWelcomePos((Float) value);
        ViewPagerAnimator.ofFloat(vpIntro,posProvider1,posPeroperty1);
        /*Frag1Melon Animator*/


        /*Frag1Texts Animator*/
        Provider<Number> posProvider2 = position -> mAdapterPager.getPage1TextsPos(position);
        Property<Number> posPeroperty2 = value -> mAdapterPager.setPage1TextsPos((Float) value);
        ViewPagerAnimator.ofFloat(vpIntro,posProvider2,posPeroperty2);
        /*Frag1Texts Animator*/

        /*Frag1Image Animator*/
        Provider<Number> posProvider3 = position -> mAdapterPager.getPage1ImagePos(position);
        Property<Number> posPeroperty3 = value -> mAdapterPager.setPage1ImagePos((Float) value);
        ViewPagerAnimator.ofFloat(vpIntro,posProvider3,posPeroperty3);
        /*Frag1Image Animator*/


        /*Frag2Image Animator*/
        Provider<Number> posProvider4 = position -> mAdapterPager.getPage2ImagePos(position);
        Property<Number> posPeroperty4 = value -> mAdapterPager.setPage2ImagePos((Float) value);
        ViewPagerAnimator.ofFloat(vpIntro,posProvider4,posPeroperty4);
        /*Frag2Image Animator*/

        /*Frag2Text Animator*/
        Provider<Number> posProvider5 = position -> mAdapterPager.getPage2TextPos(position);
        Property<Number> posPeroperty5 = value -> mAdapterPager.setPage2TextPos((Float) value);
        ViewPagerAnimator.ofFloat(vpIntro,posProvider5,posPeroperty5);
        /*Frag2Text Animator*/


        /*Frag3Image Animator*/
        Provider<Number> posProvider6 = position -> mAdapterPager.getPage3ImagePos(position);
        Property<Number> posPeroperty6 = value -> mAdapterPager.setPage3ImagePos((Float) value);
        ViewPagerAnimator.ofFloat(vpIntro,posProvider6,posPeroperty6);
        /*Frag3Image Animator*/


        /*Frag3Text Animator*/
        Provider<Number> posProvider7 = position -> mAdapterPager.getPage3TextPos(position);
        Property<Number> posPeroperty7 = value -> mAdapterPager.setPage3TextPos((Float) value);
        ViewPagerAnimator.ofFloat(vpIntro,posProvider7,posPeroperty7);
        /*Frag3Text Animator*/
    }

    @Override
    public void openEnterActivity() {
        EnterActivity.start(this);
        finish();
    }
}