package com.base.baseproject.mvp.fragment.pager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.base.baseproject.R;
import com.base.baseproject.mvp.base.BaseFragment;
import com.base.baseproject.viewhelper.adapter.AdapterFragPager;
import com.base.baseproject.viewhelper.widget.AppViewPager;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class PagerFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pager;
    }

    @BindView(R.id.vp_pager)
    AppViewPager vpPager;

    @BindView(R.id.tl_pager)
    TabLayout tlPager;

    @Inject
    AdapterFragPager mAdapterFragPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vpPager.setAdapter(mAdapterFragPager);
        tlPager.setupWithViewPager(vpPager);
    }
}
