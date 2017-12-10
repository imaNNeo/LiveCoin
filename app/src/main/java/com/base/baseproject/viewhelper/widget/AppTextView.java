package com.base.baseproject.viewhelper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.base.baseproject.R;

/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class AppTextView extends AppCompatTextView {
    private String fontName;

    public AppTextView(Context context) {
        super(context);
        fontName = context.getString(R.string.font_default);
        init(context);
    }

    public AppTextView(Context context, AttributeSet attrs){
        super(context, attrs);
        TypedArray a=getContext().obtainStyledAttributes(
                attrs,
                R.styleable.AppTextView);

        try {
            fontName = a.getString(R.styleable.AppTextView_tv_font);
        } catch (Exception e){
            fontName = context.getString(R.string.font_default);
        }

        //Don't forget this
        a.recycle();

        init(context);
    }


    void init(Context ctx){
        if(fontName == null)
            fontName = ctx.getString(R.string.font_default);
        Typeface tf = Typeface.createFromAsset(ctx.getAssets(),"fonts/" + fontName);
        setTypeface(tf);
    }

    @Override
    public void setError(CharSequence error) {
        if(error==null){
            setFocusable(false);
            setFocusableInTouchMode(false);
        }else{
            setFocusableInTouchMode(true);
            setFocusable(true);
            requestFocus();
        }
        super.setError(error);
    }

    @Override
    public void setError(CharSequence error, Drawable icon) {
        if(error==null){
            setFocusable(false);
            setFocusableInTouchMode(false);
        }else{
            setFocusableInTouchMode(true);
            setFocusable(true);
            requestFocus();
        }
        super.setError(error, icon);
    }

}