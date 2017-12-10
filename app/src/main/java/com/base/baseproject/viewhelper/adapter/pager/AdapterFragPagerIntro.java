package com.base.baseproject.viewhelper.adapter.pager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.base.baseproject.R;
import com.base.baseproject.mvp.fragment.intro_pager.FragIntro1;
import com.base.baseproject.mvp.fragment.intro_pager.FragIntro2;
import com.base.baseproject.mvp.fragment.intro_pager.FragIntro3;
import com.base.baseproject.viewhelper.widget.AppViewPager;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class AdapterFragPagerIntro extends FragmentPagerAdapter {
    private static final byte PAGES_COUNT = 3;

    public static final byte USER_INTRO1_POS = 0;
    public FragIntro1 fragIntro1;

    public static final byte USER_INTRO2_POS = 1;
    public FragIntro2 fragIntro2;

    public static final byte USER_INTRO3_POS = 2;
    public FragIntro3 fragIntro3;

    Context mContext;
    AppViewPager parentViewPager;


    public AdapterFragPagerIntro(Context ctx, FragmentManager fm) {
        super(fm);
        mContext = ctx;
    }

    public void setParentViewPager(AppViewPager pager){
        parentViewPager = pager;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case USER_INTRO1_POS :
                fragIntro1 = FragIntro1.getInstance();
                return fragIntro1;

            case USER_INTRO2_POS :
                fragIntro2 = FragIntro2.getInstance();
                return fragIntro2;


            case USER_INTRO3_POS :
                fragIntro3 = FragIntro3.getInstance();
                return fragIntro3;
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



    public Integer getColour(int position) {
        switch (position){
            case USER_INTRO1_POS :
                return ContextCompat.getColor(mContext, R.color.intro_bg_color1);

            case USER_INTRO2_POS :
                return ContextCompat.getColor(mContext,R.color.intro_bg_color2);

            case USER_INTRO3_POS :
                return ContextCompat.getColor(mContext,R.color.intro_bg_color3);
        }

        return 0;
    }


    public Float getPage1WelcomePos(int pos) {
        switch (pos){
            case USER_INTRO1_POS :
                if(fragIntro1!=null)
                    return fragIntro1.getWelcomeStartPos();

            case USER_INTRO2_POS :
                if(fragIntro1!=null)
                    return fragIntro1.getWelcomeEndPos();


            case USER_INTRO3_POS :
                return 0f;
        }

        return 0f;
    }
    public void setPageWelcomePos(Float value) {
        Log.d("SS","value = " + value);
        fragIntro1.setWelcomePos(value);
    }

    public Float getPage1TextsPos(int pos) {
        switch (pos){
            case USER_INTRO1_POS :
                if(fragIntro1!=null)
                    return fragIntro1.getTextsStartPos();

            case USER_INTRO2_POS :
                if(fragIntro1!=null)
                    return fragIntro1.getTextsEndPos();


            case USER_INTRO3_POS :
                return 0f;
        }

        return 0f;
    }
    public void setPage1TextsPos(Float value) {
        Log.d("SS","value = " + value);
        fragIntro1.setTextsPos(value);
    }

    public Float getPage1ImagePos(int pos){
        switch (pos){
            case USER_INTRO1_POS :
                if(fragIntro1!=null)
                    return fragIntro1.getImageStartPos();

            case USER_INTRO2_POS :
                if(fragIntro1!=null)
                    return fragIntro1.getImageEndPos();


            case USER_INTRO3_POS :
                return 0f;
        }

        return 0f;
    }
    public void setPage1ImagePos(Float value) {
        Log.d("SS","value = " + value);
        fragIntro1.setImagePos(value);
    }

    public Float getPage2ImagePos(int pos) {
        switch (pos){
            case USER_INTRO1_POS :
                if(fragIntro2!=null)
                    return fragIntro2.getImageStartPos();

            case USER_INTRO2_POS :
                if(fragIntro2!=null)
                    return fragIntro2.getImageMiddlePos();


            case USER_INTRO3_POS :
                if(fragIntro2!=null)
                    return fragIntro2.getImageEndPos();
        }

        return 0f;
    }
    public void setPage2ImagePos(Float value) {
        Log.d("SS","value = " + value);
        fragIntro2.setImagePos(value);
    }

    public Float getPage2TextPos(int pos) {
        switch (pos){
            case USER_INTRO1_POS :
                if(fragIntro2!=null)
                    return fragIntro2.getTextStartPos();

            case USER_INTRO2_POS :
                if(fragIntro2!=null)
                    return fragIntro2.getTextMiddlePos();


            case USER_INTRO3_POS :
                if(fragIntro2!=null)
                    return fragIntro2.getTextEndPos();
        }

        return 0f;
    }
    public void setPage2TextPos(Float value) {
        Log.d("SS","value = " + value);
        fragIntro2.setTextPos(value);
    }

    public Float getPage3ImagePos(int pos) {
        switch (pos){
            case USER_INTRO1_POS :
                return 0f;

            case USER_INTRO2_POS :
                if(fragIntro3!=null)
                    return fragIntro3.getImageStartPos();


            case USER_INTRO3_POS :
                if(fragIntro3!=null)
                    return fragIntro3.getImageEndPos();
        }

        return 0f;
    }
    public void setPage3ImagePos(Float value) {
        Log.d("SS","value = " + value);
        fragIntro3.setImagePos(value);
    }


    public Float getPage3TextPos(int pos) {
        switch (pos){
            case USER_INTRO1_POS :
                return 0f;

            case USER_INTRO2_POS :
                if(fragIntro3!=null)
                    return fragIntro3.getTextStartPos();


            case USER_INTRO3_POS :
                if(fragIntro3!=null)
                    return fragIntro3.getTextEndPos();
        }

        return 0f;
    }
    public void setPage3TextPos(Float value) {
        Log.d("SS","value = " + value);
        fragIntro3.setTextPos(value);
    }
}
