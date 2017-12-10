package com.base.baseproject.mvp.fragment.intro_pager;

import com.base.baseproject.R;
import com.base.baseproject.mvp.base.BaseFragment;
import com.base.baseproject.viewhelper.widget.AppImageView;
import com.base.baseproject.viewhelper.widget.AppTextView;

import butterknife.BindView;


/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class FragIntro2 extends BaseFragment{

    private static FragIntro2 mFragIntro2;
    public static FragIntro2 getInstance(){
        if(mFragIntro2 ==null)
            mFragIntro2 = new FragIntro2();

        return mFragIntro2;
    }


    @BindView(R.id.iv_coin)
    AppImageView ivCoin;

    @BindView(R.id.tv_text)
    AppTextView tvText;

    @Override
    public int getLayoutId() {
        return R.layout.frag_intro2;
    }

    public Float getImageStartPos(){
        return 0f;
    }
    public Float getImageMiddlePos(){
        return -(360*2f);
    }
    public Float getImageEndPos(){
        return -(360*4f);
    }
    public void setImagePos(Float f){
        ivCoin.setRotation(f);
    }

    public Float getTextStartPos(){
        return 0f;
    }
    public Float getTextMiddlePos(){
        return 1f;
    }
    public Float getTextEndPos(){
        return 0f;
    }
    public void setTextPos(Float f){
        tvText.setScaleX(f);
        tvText.setScaleY(f);
        tvText.setAlpha(f);
    }
}
