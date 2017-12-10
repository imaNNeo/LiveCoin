package com.base.baseproject.viewhelper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.util.AttributeSet;

import com.base.baseproject.R;

/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class AppEditText extends AppCompatEditText {
    private static final int HINT_SMALL_SIZE_DEFAULT = 2;

    private String fontName;
    private int hintSmallSize;

    public AppEditText(Context context) {
        super(context);
        fontName = context.getString(R.string.font_default);
        hintSmallSize = HINT_SMALL_SIZE_DEFAULT;
        init(context);
    }

    public AppEditText(Context context, AttributeSet attrs){
        super(context, attrs);
        TypedArray a=getContext().obtainStyledAttributes(
                attrs,
                R.styleable.AppTextView);

        try {
            fontName = a.getString(R.styleable.AppEditText_et_font);
        } catch (Exception e){
            fontName = context.getString(R.string.font_default);
        }

        try {
            hintSmallSize = a.getInteger(R.styleable.AppEditText_et_hint_small_size,HINT_SMALL_SIZE_DEFAULT);
        } catch (Exception e){
            hintSmallSize = HINT_SMALL_SIZE_DEFAULT;
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

        if(getHint()!=null) {
            String htmlStr = "";

            for(int i=1;i<=hintSmallSize;i++){
                htmlStr += "<small>";
            }
            htmlStr +=getHint().toString();
            for(int i=1;i<=hintSmallSize;i++){
                htmlStr += "</small>";
            }

            setHint(Html.fromHtml(htmlStr));
        }


        setTextColor(Color.BLACK);
        setHintTextColor(Color.GRAY);
    }

    @Override
    public void setError(CharSequence error){
        super.setError(error);
        requestFocus();
    }

    @Override
    public void setError(CharSequence error, Drawable icon) {
        super.setError(error, icon);
        requestFocus();
    }
}
