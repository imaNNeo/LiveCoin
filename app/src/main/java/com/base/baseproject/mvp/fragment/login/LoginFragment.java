package com.base.baseproject.mvp.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.base.baseproject.R;
import com.base.baseproject.listeners.OnActionClickedListener;
import com.base.baseproject.mvp.activity.main.MainActivity;
import com.base.baseproject.mvp.base.BaseFragment;
import com.base.baseproject.utils.ActivityHolder;
import com.base.baseproject.utils.validation.Validation;
import com.base.baseproject.viewhelper.widget.AppEditText;
import com.base.baseproject.viewhelper.widget.AppLoadingButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class LoginFragment extends BaseFragment implements LoginView{

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }


    @Inject
    LoginPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    @BindView(R.id.et_email)
    AppEditText etEmail;

    @BindView(R.id.et_password)
    AppEditText etPassword;

    @BindView(R.id.btn_login)
    AppLoadingButton btnLogin;

    @OnClick(R.id.btn_login)
    public void onLoginClicked(){
        mPresenter.onLoginClicked();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onAttach(this);
    }

    @Override
    public String getEmailText() {
        return etEmail.getText().toString();
    }

    @Override
    public String getPasswordText() {
        return etPassword.getText().toString();
    }

    @Override
    public void onEmailValidateError(int errorValidationCode) {
        etEmail.setError(Validation.getMessage(getActivity(),errorValidationCode));
    }

    @Override
    public void onPasswordValidateError(int errorValidationCode) {
        etPassword.setError(Validation.getMessage(getActivity(),errorValidationCode));
    }

    @Override
    public void showLoading() {
        super.showPagesLoading();
    }

    @Override
    public void hideLoading() {
        super.hidePagesLoading();
    }

    @Override
    public void showServerMessage(String message) {
        showMessageToastLong(message);
    }

    @Override
    public void gotoMain() {
        MainActivity.start(getContext());
    }

    @Override
    public void closeEntranceActivities() {
        ActivityHolder.getInstance().closeEntranceActivities();
    }


    @Override
    public void showSystemError(String message, OnActionClickedListener listener) {
        super.showSystemErrorOverlay(message,listener);
    }

    @Override
    public void hideSystemError() {
        super.hideSystemErrorOverlay();
    }

    @Override
    public void showInternetRetry(OnActionClickedListener listener) {
        super.showInternetRetryOverlay(listener);
    }


    @Override
    public void hideInternetRetry() {
        super.hideInternetRetryOverlay();
    }

}