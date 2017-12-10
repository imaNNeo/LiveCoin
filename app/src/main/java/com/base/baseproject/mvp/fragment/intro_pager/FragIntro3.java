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
public class FragIntro3 extends BaseFragment{

    private static FragIntro3 mFragIntro3;
    public static FragIntro3 getInstance(){
        if(mFragIntro3 ==null)
            mFragIntro3 = new FragIntro3();

        return mFragIntro3;
    }



    @BindView(R.id.iv_melon)
    AppImageView ivMelon;

    @BindView(R.id.tv_text)
    AppTextView tvText;

    @BindView(R.id.rl_container)
    RelativeLayout rlContainer;

    @Override
    public int getLayoutId() {
        return R.layout.frag_intro3;
    }

    public float getImageStartPos() {
        return 0f;
    }

    public float getImageEndPos() {
        return 1f;
    }

    public void setImagePos(float pos){
        ivMelon.setScaleX(pos);
        ivMelon.setScaleY(pos);
        ivMelon.setAlpha(pos);
    }


    public float getTextStartPos() {
        return 0f;
    }

    public float getTextEndPos() {
        return 1f;
    }

    public void setTextPos(float pos){
        Log.d("SS","setTextPos3 " + pos);
        tvText.setAlpha(pos);
    }

}
