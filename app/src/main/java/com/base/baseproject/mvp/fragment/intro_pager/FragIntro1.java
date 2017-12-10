package com.base.baseproject.mvp.fragment.intro_pager;

import android.util.Log;
import android.widget.RelativeLayout;

import com.base.baseproject.R;
import com.base.baseproject.mvp.base.BaseFragment;
import com.base.baseproject.viewhelper.widget.AppImageView;
import com.base.baseproject.viewhelper.widget.AppTextView;

import butterknife.BindView;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class FragIntro1 extends BaseFragment{

    private static FragIntro1 mFragIntro1;
    public static FragIntro1 getInstance(){
        if(mFragIntro1 ==null)
            mFragIntro1 = new FragIntro1();

        return mFragIntro1;
    }


    @BindView(R.id.rl_container)
    public RelativeLayout rlContainer;

    @BindView(R.id.tv_welcome)
    public AppTextView tvWelcome;

    @BindView(R.id.iv_melon)
    public AppImageView ivMelon;

    @BindView(R.id.tv_text)
    public AppTextView tvText;

    @Override
    public int getLayoutId() {
        return R.layout.frag_intro1;
    }

    float welcomeStartPos = 0;
    public float getWelcomeStartPos() {
        if(welcomeStartPos ==0)
            welcomeStartPos = tvWelcome.getY();
        return welcomeStartPos;
    }

    float welcomeEndPos = 0;
    public float getWelcomeEndPos() {
        if(welcomeEndPos ==0 )
            welcomeEndPos = 0 - tvWelcome.getHeight();
        return welcomeEndPos;
    }

    public void setWelcomePos(float pos){
        tvWelcome.setY(pos);
    }


    float textsStartPos = 0;
    public float getTextsStartPos() {
        if(textsStartPos==0)
            textsStartPos = tvText.getY();
        return textsStartPos;
    }

    float textsEndPos = 0;
    public float getTextsEndPos() {
        if(textsEndPos==0)
            textsEndPos = rlContainer.getHeight();
        return textsEndPos;
    }


    public float getImageStartPos(){
        return 1f;
    }
    public float getImageEndPos(){
        return 0f;
    }
    public void setImagePos(float f){
        Log.d("SS","setImagePos" + f);
        ivMelon.setAlpha(f);
        ivMelon.setScaleX(f);
        ivMelon.setScaleY(f);
    }

    public void setTextsPos(float pos){
        tvText.setY(pos);
    }
}
