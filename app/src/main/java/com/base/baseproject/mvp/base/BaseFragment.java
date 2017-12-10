package com.base.baseproject.mvp.base;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;

import com.base.baseproject.R;
import com.base.baseproject.data.api.ApiHelper;
import com.base.baseproject.data.db.DbHelper;
import com.base.baseproject.data.preferences.PreferencesHelper;
import com.base.baseproject.data.user.UserHelper;
import com.base.baseproject.di.component.DaggerFragmentComponent;
import com.base.baseproject.di.component.FragmentComponent;
import com.base.baseproject.di.module.FragmentModule;
import com.base.baseproject.listeners.OnActionClickedListener;
import com.base.baseproject.rx.SchedulerProvider;
import com.base.baseproject.viewhelper.widget.PagesInternetErrorView;
import com.base.baseproject.viewhelper.widget.PagesLoadingView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Amir Abdi on 01/05/2017.
 */

public abstract class BaseFragment extends Fragment{

    public abstract int getLayoutId();

    private View view;
    private String mTitle;
    public boolean isViewCreated = false;

    Unbinder mUnbinder;

    PagesLoadingView plView;
    PagesInternetErrorView pagesInternetErrorView;
    PagesInternetErrorView pagesSystemErrorView;

    FrameLayout flMainContainer;
    View preventClickView;


    @Inject
    public UserHelper mUserHelper;

    @Inject
    public PreferencesHelper mPreferencesHelper;

    @Inject
    public ApiHelper mApiHelper;

    @Inject
    public DbHelper mDbHelper;

    @Inject
    public SchedulerProvider mSchedulerProvider;

    FragmentComponent mFragmentComponent;


    public FragmentComponent getComponent() {
        if(mFragmentComponent==null){
            mFragmentComponent =
                    DaggerFragmentComponent.builder()
                            .activityComponent(getBaseActivity().getComponent())
                            .fragmentModule(new FragmentModule(this))
                            .build();
        }
        return mFragmentComponent;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        isViewCreated = true;

        flMainContainer = new FrameLayout(getActivity());
        flMainContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        flMainContainer.addView(view);
        preventClickView = new View(getActivity());
        preventClickView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        preventClickView.setClickable(true);
        preventClickView.setOnClickListener(v-> Log.d("SS","preventViewClicked"));
        preventClickView.setVisibility(View.GONE);
        flMainContainer.addView(preventClickView);
        return flMainContainer;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;


        /*PagesLoadingView*/
        plView = new PagesLoadingView(getActivity());
        plView.setLayoutParams(params);
        flMainContainer.addView(plView);
        plView.setAlpha(0f);
        plView.setScaleX(0f);
        plView.setScaleY(0f);
        /*PagesLoadingView*/




        /*PagesSystemErrorView (internet error)*/
        pagesInternetErrorView = new PagesInternetErrorView(getActivity());
        pagesInternetErrorView.setLayoutParams(params);
        pagesInternetErrorView.setAlpha(0f);
        pagesInternetErrorView.setScaleX(0f);
        pagesInternetErrorView.setScaleY(0f);
        pagesInternetErrorView.setVisibility(View.INVISIBLE);
        pagesInternetErrorView.setStyle(PagesInternetErrorView.PIE_STYLE_INTERNET_ERROR);
        pagesInternetErrorView.tvTitle.setText(R.string.internet_problem);
        pagesInternetErrorView.tvDescription.setText(R.string.internet_problem_hint);
        flMainContainer.addView(pagesInternetErrorView);
        /*PagesSystemErrorView (internet error)*/





        /*PagesSystemErrorView (internet error)*/
        pagesSystemErrorView = new PagesInternetErrorView(getActivity());
        pagesSystemErrorView.setLayoutParams(params);
        pagesSystemErrorView.setAlpha(0f);
        pagesSystemErrorView.setScaleX(0f);
        pagesSystemErrorView.setScaleY(0f);
        pagesSystemErrorView.setVisibility(View.INVISIBLE);
        pagesSystemErrorView.setStyle(PagesInternetErrorView.PIE_STYLE_SYSTEM_ERROR);
        pagesSystemErrorView.tvTitle.setText(R.string.system_error);
        pagesSystemErrorView.tvDescription.setText(R.string.system_error_hint);
        flMainContainer.addView(pagesSystemErrorView);
        /*PagesSystemErrorView (internet error)*/
    }

    public void showPagesLoading(){
        showPagesLoading(true);
    }

    public void showPagesLoading(boolean hideKeyboard){
        plView.animate().alpha(1).scaleX(1).scaleY(1).setDuration(300).setInterpolator(new BounceInterpolator()).start();
        if(hideKeyboard)
            getBaseActivity().hideSoftKeyboard();
    }
    public void hidePagesLoading(){
        plView.animate().alpha(0).scaleX(0).scaleY(0).setDuration(300).setInterpolator(new BounceInterpolator()).start();
    }


    private void showPagesInternetError(){
        setPreventViewVisible(true);
        pagesInternetErrorView.animate().alpha(1).scaleX(1).scaleY(1).setDuration(300).setInterpolator(new BounceInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        pagesInternetErrorView.setVisibility(View.VISIBLE);
                        pagesInternetErrorView.ivClose.setOnClickListener(v -> hidePagesInternetError());
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                }).start();
    }
    private void hidePagesInternetError(){
        setPreventViewVisible(false);
        pagesInternetErrorView.animate().alpha(0).scaleX(0).scaleY(0).setDuration(300).setInterpolator(new BounceInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        pagesInternetErrorView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                }).start();
    }


    private void showPagesSystemError(){
        setPreventViewVisible(true);
        pagesSystemErrorView.animate().alpha(1).scaleX(1).scaleY(1).setDuration(300).setInterpolator(new BounceInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        pagesSystemErrorView.setVisibility(View.VISIBLE);
                        pagesSystemErrorView.ivClose.setOnClickListener(v -> hideSystemErrorOverlay());
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                }).start();
    }
    private void hidePagesSystemError(){
        setPreventViewVisible(false);
        pagesSystemErrorView.animate().alpha(0).scaleX(0).scaleY(0).setDuration(300).setInterpolator(new BounceInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        pagesSystemErrorView.setVisibility(View.INVISIBLE);
                        pagesSystemErrorView.ivClose.setOnClickListener(null);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                }).start();
    }


    private void setPreventViewVisible(boolean b){
        preventClickView.setVisibility(b?View.VISIBLE:View.GONE);
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

    public String getFragTitle(){
        mTitle = getString(R.string.app_name);
        return mTitle;
    }
    public void setFragTitle(String title){
        mTitle = title;
    }

    public void showWaitDialog(){
        if(getActivity() instanceof BaseActivity)
            ((BaseActivity)getActivity()).showWaitDialog();
    }
    public void hideWaitDialog(){
        if(getActivity() instanceof BaseActivity)
            ((BaseActivity)getActivity()).hideWaitDialog();
    }

    public void showMessageSnackShort(String message){
        if(getActivity() instanceof BaseActivity)
            ((BaseActivity)getActivity()).showMessageSnackShort(message);
    }
    public void showMessageSnackLong(String message){
        if(getActivity() instanceof BaseActivity)
            ((BaseActivity)getActivity()).showMessageSnackLong(message);
    }

    public void showMessageToastShort(String message){
        if(getActivity() instanceof BaseActivity)
            ((BaseActivity)getActivity()).showMessageToastShort(message);
    }
    public void showMessageToastLong(String message){
        if(getActivity() instanceof BaseActivity)
            ((BaseActivity)getActivity()).showMessageToastLong(message);
    }

    public void showInternetRetryOverlay(OnActionClickedListener onActionClickedListener){
        pagesInternetErrorView.setListener(onActionClickedListener);
        showPagesInternetError();
    }
    public void hideInternetRetryOverlay(){
        pagesInternetErrorView.setListener(null);
        hidePagesInternetError();
    }

    public void showSystemErrorOverlay(String message,OnActionClickedListener listener){
        if(message==null)
            pagesSystemErrorView.tvDescription.setText(R.string.system_error_hint);
        else
            pagesSystemErrorView.tvDescription.setText(message);

        pagesSystemErrorView.setListener(listener);
        showPagesSystemError();
    }
    public void hideSystemErrorOverlay(){
        pagesSystemErrorView.setListener(null);
        hidePagesSystemError();
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }
}