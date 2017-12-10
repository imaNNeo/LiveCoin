package com.base.baseproject.viewhelper.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.base.baseproject.R;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class PagesLoadingView extends RelativeLayout {
    public PagesLoadingView(Context context) {
        super(context);
        init(context);
    }

    public PagesLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PagesLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        inflate(context, R.layout.pages_loading_view,this);
    }
}