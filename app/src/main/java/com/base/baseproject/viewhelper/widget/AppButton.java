package com.base.baseproject.viewhelper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.base.baseproject.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class AppButton extends AppCompatButton {
    public static final byte BUTTON_STYLE_WHITE = 0;
    public static final byte BUTTON_STYLE_BLACK = 1;
    public static final byte BUTTON_STYLE_ORANGE = 2;
    public static final byte BUTTON_STYLE_GRAY = 3;
    public static final byte BUTTON_STYLE_BLUE = 4;

    // Declare the @IntDef for these constants
    @IntDef({BUTTON_STYLE_WHITE, BUTTON_STYLE_BLACK,
            BUTTON_STYLE_ORANGE, BUTTON_STYLE_GRAY,BUTTON_STYLE_BLUE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonStyle {}

    @AppButton.ButtonStyle
    private static int getButtonStyle(int style){
        switch (style){
            case BUTTON_STYLE_WHITE: return BUTTON_STYLE_WHITE;
            case BUTTON_STYLE_BLACK: return BUTTON_STYLE_BLACK;
            case BUTTON_STYLE_ORANGE: return BUTTON_STYLE_ORANGE;
            case BUTTON_STYLE_GRAY: return BUTTON_STYLE_GRAY;
            case BUTTON_STYLE_BLUE: return BUTTON_STYLE_BLUE;
            default:return BUTTON_STYLE_WHITE;
        }
    }


    @ButtonStyle
    private int selectedButtonStyle = BUTTON_STYLE_WHITE;

    private String fontName;
    private Context mContext;

    public AppButton(Context context) {
        super(context);
        fontName = context.getString(R.string.font_default);
        init(context);
    }

    public AppButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a=getContext().obtainStyledAttributes(
                attrs,
                R.styleable.AppButton);

        try {
            fontName = a.getString(R.styleable.AppButton_btn_font);
        } catch (Exception e){
            fontName = context.getString(R.string.font_default);
        }

        try {
            selectedButtonStyle = getButtonStyle(a.getInt(R.styleable.AppButton_btn_style,BUTTON_STYLE_WHITE));
        } catch (Exception e){
            selectedButtonStyle = BUTTON_STYLE_WHITE;
        }

        //Don't forget this
        a.recycle();

        init(context);
    }

    void init(Context ctx){
        mContext = ctx;
        if(fontName == null)
            fontName = ctx.getString(R.string.font_default);
        Typeface tf = Typeface.createFromAsset(ctx.getAssets(),"fonts/" + fontName);
        setTypeface(tf);

        refreshButtonStyle();
    }


    public void setButtonStyle(@ButtonStyle int buttonStyle){
        selectedButtonStyle = buttonStyle;
        refreshButtonStyle();
    }

    private void refreshButtonStyle() {
        switch (selectedButtonStyle){
            case BUTTON_STYLE_WHITE:
                setWhite();
                break;
            case BUTTON_STYLE_BLACK:
                setBlack();
                break;
            case BUTTON_STYLE_ORANGE:
                setOrange();
                break;
            case BUTTON_STYLE_GRAY:
                setGray();
                break;
            case BUTTON_STYLE_BLUE :
                setBlue();
                break;
        }
    }

    private void setBlack(){
        setBackgroundColor(ContextCompat.getColor(mContext,R.color.button_black));
        setTextColor(ContextCompat.getColor(mContext,R.color.button_white));
    }

    private void setWhite(){
        setBackgroundColor(ContextCompat.getColor(mContext,R.color.button_white));
        setTextColor(ContextCompat.getColor(mContext,R.color.button_black));
    }

    private void setOrange(){
        setBackgroundColor(ContextCompat.getColor(mContext,R.color.button_orange));
        setTextColor(ContextCompat.getColor(mContext,R.color.white));
    }

    private void setGray(){
        setBackgroundColor(ContextCompat.getColor(mContext,R.color.button_gray));
        setTextColor(ContextCompat.getColor(mContext,R.color.white));
    }

    private void setBlue(){
        setBackgroundColor(ContextCompat.getColor(mContext,R.color.button_blue));
        setTextColor(ContextCompat.getColor(mContext,R.color.white));
    }
}