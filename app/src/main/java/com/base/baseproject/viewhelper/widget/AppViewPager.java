package com.base.baseproject.viewhelper.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class AppViewPager extends ViewPager{
    private boolean enabledSwiping = true;

    public AppViewPager(Context context) {
        super(context);
    }

    public AppViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabledSwiping) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabledSwiping) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setSwipingEnabled(boolean enabled) {
        this.enabledSwiping = enabled;
    }

}
