package com.base.baseproject.mvp.fragment;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.base.baseproject.R;
import com.base.baseproject.mvp.base.BaseFragment;
import com.base.baseproject.mvp.fragment.login.LoginFragment;
import com.base.baseproject.mvp.fragment.main.MainFragment;
import com.base.baseproject.mvp.fragment.pager.PagerFragment;


/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class FragmentsHandler {
    public FragmentManager fragManager;
    @IdRes
    int fragsContainerResId = 0;

    public FragmentsHandler(FragmentManager fm){
        fragManager = fm;
    }

    public String getCurrentFragTag() {

        if (fragManager.getBackStackEntryCount() == 0) {
            return null;
        }

        String tag = fragManager.getBackStackEntryAt(fragManager.getBackStackEntryCount() - 1)
                .getName();

        return tag;
    }
    public BaseFragment getCurrentFragment(){
        return (BaseFragment) fragManager.findFragmentByTag(getCurrentFragTag());
    }


    public void setFragsContainerResId(@IdRes int resId){
        fragsContainerResId = resId;
    }

    private void openFragment(BaseFragment frag, String tag, boolean withAnim){
        if(fragsContainerResId==0){
            Log.d("SS","fragsContainerResId==0");
            return;
        }

//        if(getCurrentFragment()!=null && getCurrentFragment().equals(frag))return;

        android.support.v4.app.FragmentTransaction ft = fragManager.beginTransaction();
        if(withAnim)
            ft.setCustomAnimations(
                    R.anim.frags_enter_right,
                    R.anim.frags_exit_right,
                    R.anim.frags_enter_right,
                    R.anim.frags_exit_right);
        ft.add(fragsContainerResId, frag, tag);
        ft.addToBackStack(tag);
        ft.commit();
    }

    private void openFragmentsMain(BaseFragment frag, String tag, boolean withAnim){
        if(fragsContainerResId==0){
            Log.d("SS","fragsContainerResId==0");
            return;
        }

        android.support.v4.app.FragmentTransaction ft = fragManager.beginTransaction();
        if(withAnim)
            ft.setCustomAnimations(
                    R.anim.frags_enter_right,
                    R.anim.frags_exit_right,
                    R.anim.frags_enter_right,
                    R.anim.frags_exit_right);
        ft.add(fragsContainerResId, frag, tag);
        ft.addToBackStack(tag);
        ft.commit();
    }


    public LoginFragment loginFragment;
    public void openLoginFragment(boolean withAnim){
        loginFragment = new LoginFragment();
        openFragment(loginFragment,LoginFragment.class.getName(),false);
    }

    public PagerFragment pagerFragment;
    public void openPagerFragment(){
        pagerFragment = new PagerFragment();
        openFragment(pagerFragment,PagerFragment.class.getName(),false);
    }

    public MainFragment mainFragment;
    public void openMainFragment(){
        mainFragment = new MainFragment();
        openFragment(mainFragment,MainFragment.class.getName(),false);
    }

    public void removeFragment(BaseFragment frag){
        fragManager.beginTransaction().remove(frag).commit();
    }
    public void gotoPreviousFrag(){
        fragManager.popBackStack();
    }

    public void popAllFragments() {
        for(int i = 0; i < fragManager.getBackStackEntryCount(); ++i) {
            fragManager.popBackStack();
        }
    }

    public void popAllFragmentsTo(String tag) {
        for(int i = 0; i < fragManager.getBackStackEntryCount(); ++i) {
            FragmentManager.BackStackEntry be = fragManager.getBackStackEntryAt(i);
                fragManager.popBackStack();
            if(tag.equals(be.getName())) {
                return;
            }
        }
    }
}




