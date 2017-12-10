package com.base.baseproject.mvp.fragment.pager.page1;

import com.base.baseproject.R;
import com.base.baseproject.mvp.base.BaseFragment;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class Page1Fragment extends BaseFragment{

    public static Page1Fragment getInstance() {
        return new Page1Fragment();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_page1;
    }


}
