package com.base.baseproject.viewhelper.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.base.baseproject.mvp.fragment.pager.page1.Page1Fragment;
import com.base.baseproject.mvp.fragment.pager.page2.Page2Fragment;
import com.base.baseproject.mvp.fragment.pager.page3.Page3Fragment;

import butterknife.ButterKnife;


/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class AdapterFragPager extends FragmentStatePagerAdapter {
    private static final byte PAGES_COUNT = 3;

    public static final byte PAGE_1_POS = 0;
    public Page1Fragment page1Fragment;
    String page1Title = "Page 1";

    public static final byte PAGE_2_POS = 1;
    public Page2Fragment page2Fragment;
    String page2Title = "Page 2";

    public static final byte PAGE_3_POS = 2;
    public Page3Fragment page3Fragment;

    Context mContext;
    ViewPager parentViewPager;
    String page3Title = "Page 3";


    public AdapterFragPager(Context ctx, FragmentManager fm) {
        super(fm);
        mContext = ctx;

        if(ctx instanceof Activity)
            ButterKnife.bind((Activity) ctx);
    }

    public void setParentViewPager(ViewPager pager){
        parentViewPager = pager;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case PAGE_1_POS:
                page1Fragment = Page1Fragment.getInstance();
                return page1Fragment;

            case PAGE_2_POS:
                page2Fragment = Page2Fragment.getInstance();
                return page2Fragment;

            case PAGE_3_POS:
                page3Fragment = Page3Fragment.getInstance();
                return page3Fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }

    public void gotoPage(int pos){
        if(parentViewPager!=null)
            parentViewPager.setCurrentItem(pos);
    }

    public void gotoNext(){
        if(parentViewPager.getCurrentItem()==PAGES_COUNT-1)
            return;

        gotoPage(parentViewPager.getCurrentItem()+1);
    }

    public void gotoPrevious(){
        if(parentViewPager.getCurrentItem()==0)
            return;

        gotoPage(parentViewPager.getCurrentItem()-1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case PAGE_1_POS :
                return page1Title;

            case PAGE_2_POS :
                return page2Title;

            case PAGE_3_POS :
                return page3Title;

        }

        return  page1Title;
    }
}