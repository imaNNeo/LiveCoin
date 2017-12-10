package com.base.baseproject.viewhelper.widget;

import android.content.Context;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.base.baseproject.R;
import com.base.baseproject.listeners.OnActionClickedListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class PagesInternetErrorView extends RelativeLayout {
    public static final byte PIE_STYLE_SYSTEM_ERROR = 1;
    public static final byte PIE_STYLE_INTERNET_ERROR = 2;
    @IntDef({PIE_STYLE_SYSTEM_ERROR,PIE_STYLE_INTERNET_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PagesInternetErrorStyle{}


    @PagesInternetErrorStyle
    private int currentStyle = PIE_STYLE_INTERNET_ERROR;


    OnActionClickedListener mListener;

    @BindView(R.id.iv_image)
    public AppImageView ivImage;

    @BindView(R.id.tv_title)
    public AppTextView tvTitle;

    @BindView(R.id.tv_description)
    public AppTextView tvDescription;

    @BindView(R.id.btn_tryAgain)
    public AppButton btnTryAgain;

    @BindView(R.id.iv_close)
    public AppImageView ivClose;



    public PagesInternetErrorView(Context context) {
        super(context);
        init(context);
    }

    public PagesInternetErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PagesInternetErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        inflate(context, R.layout.pages_internet_error_view,this);
        ButterKnife.bind(this);
    }

    private void refreshStyle() {
        switch (currentStyle){
            case PIE_STYLE_SYSTEM_ERROR:
                ivImage.setImageResource(R.drawable.ic_error_outline_white);
                ivClose.setVisibility(View.VISIBLE);
                break;

            case PIE_STYLE_INTERNET_ERROR :
                ivImage.setImageResource(R.drawable.ic_signal_wifi_off_white);
                ivClose.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick(R.id.btn_tryAgain)
    void onTryAgainClicked(){
        if(mListener!=null)mListener.onActionClicked();
    }



    public void setStyle(@PagesInternetErrorStyle int style){
        this.currentStyle = style;
        refreshStyle();
    }

    public void setListener(OnActionClickedListener listener){
        mListener = listener;
        btnTryAgain.setVisibility(mListener == null ? View.GONE : View.VISIBLE);
    }

}