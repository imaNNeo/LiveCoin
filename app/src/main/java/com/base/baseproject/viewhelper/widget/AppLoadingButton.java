package com.base.baseproject.viewhelper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import com.base.baseproject.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class AppLoadingButton extends AppConstraintLayout {
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

    @AppLoadingButton.ButtonStyle
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
    private String mText;


    public AppTextView tvText;
    public AppProgressView pvBtnLoading;

    public AppLoadingButton(Context context) {
        super(context);
        fontName = context.getString(R.string.font_default);
        init(context);
    }

    public AppLoadingButton(Context context, AttributeSet attrs) {
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

        if (attrs != null) {
            int[] attrsArray = {
                    android.R.attr.text
            };
            TypedArray array = context.obtainStyledAttributes(
                    attrs, attrsArray);
            try {
                mText = array.getString(0);
            } catch (Exception e) {
                mText = "";
            }
            array.recycle();
        }

        init(context);
    }

    void init(Context ctx){
        mContext = ctx;
        if(fontName == null)
            fontName = ctx.getString(R.string.font_default);
        Typeface tf = Typeface.createFromAsset(ctx.getAssets(),"fonts/" + fontName);


        inflate(ctx,R.layout.app_loading_button,this);

        pvBtnLoading = (AppProgressView) findViewById(R.id.pv_btnLoading);
        tvText = (AppTextView) findViewById(R.id.tv_text);
        tvText.setTypeface(tf);
        tvText.setText(mText);


        refreshButtonStyle();
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        tvText.setOnClickListener(l);
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
        tvText.setTextColor(ContextCompat.getColor(mContext,R.color.button_white));
    }

    private void setWhite(){
        setBackgroundColor(ContextCompat.getColor(mContext,R.color.button_white));
        tvText.setTextColor(ContextCompat.getColor(mContext,R.color.button_black));
    }

    private void setOrange(){
        setBackgroundColor(ContextCompat.getColor(mContext,R.color.button_orange));
        tvText.setTextColor(ContextCompat.getColor(mContext,R.color.white));
    }

    private void setGray(){
        setBackgroundColor(ContextCompat.getColor(mContext,R.color.button_gray));
        tvText.setTextColor(ContextCompat.getColor(mContext,R.color.black));
    }

    private void setBlue(){
        setBackgroundColor(ContextCompat.getColor(mContext,R.color.button_blue));
        tvText.setTextColor(ContextCompat.getColor(mContext,R.color.white));
    }

    public void showLoading(){
        pvBtnLoading.setVisibility(View.VISIBLE);
        tvText.setVisibility(View.INVISIBLE);
    }
    public void hideLoading(){
        pvBtnLoading.setVisibility(View.INVISIBLE);
        tvText.setVisibility(View.VISIBLE);
    }
    public boolean isLoadingShown(){
        return pvBtnLoading.getVisibility() == View.VISIBLE;
    }
}