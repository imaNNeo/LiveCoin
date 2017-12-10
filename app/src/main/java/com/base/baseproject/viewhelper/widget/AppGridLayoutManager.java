package com.base.baseproject.viewhelper.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class AppGridLayoutManager extends GridLayoutManager {
    public AppGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public AppGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public AppGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }
}
