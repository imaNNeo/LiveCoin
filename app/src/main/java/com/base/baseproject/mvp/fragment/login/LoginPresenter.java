package com.base.baseproject.mvp.fragment.login;

import com.base.baseproject.data.api.ApiHelper;
import com.base.baseproject.data.api.AppApiHelper;
import com.base.baseproject.data.api.retrofit.models.RequestObjects;
import com.base.baseproject.data.api.retrofit.models.ResponseObjects;
import com.base.baseproject.data.user.UserHelper;
import com.base.baseproject.mvp.base.loading.LoadingMvpPresenter;
import com.base.baseproject.rx.SchedulerProvider;
import com.base.baseproject.utils.validation.Validation;
import com.base.baseproject.utils.validation.ValidationHandler;

import io.reactivex.disposables.Disposable;


/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class LoginPresenter extends LoadingMvpPresenter<LoginView> {
    private static final byte RC_LOGIN = 1;

    ApiHelper mApiHelper;
    UserHelper mUserHelper;
    SchedulerProvider mSchedulerProvider;
    public LoginPresenter(ApiHelper apiHelper,
                          UserHelper userHelper,
                          SchedulerProvider schedulerProvider) {
        super();
        mApiHelper = apiHelper;
        mUserHelper = userHelper;
        mSchedulerProvider = schedulerProvider;
    }

    public void onLoginClicked() {
        if(!isViewAttached())
            return;

        tryToLogin();
    }

    private void tryToLogin(){
        String strEmail = getMvpView().getEmailText().trim();
        String strPassword = getMvpView().getPasswordText().trim();
        int code;
        if((code=ValidationHandler.isValidEmail(strEmail))!= Validation.ERROR_CODE_SUCCESS){
            getMvpView().onEmailValidateError(code);
            return;
        }

        if((code=ValidationHandler.isValidPassword(strPassword))!= Validation.ERROR_CODE_SUCCESS){
            getMvpView().onPasswordValidateError(code);
            return;
        }

        //All Fields valid
        loginFields = new RequestObjects.LoginFields();
        loginFields.setUsername(strEmail);
        loginFields.setPassword(strPassword);
        callLogin();

    }

    RequestObjects.LoginFields loginFields;
    private void callLogin(){
        if(loginFields ==null)return;
        getMvpView().showLoading();
        mApiHelper.login(loginFields)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new AppApiHelper.MySingleObserver<ResponseObjects.LoginResponse>(){
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        getDisposable().add(d);
                    }

                    @Override
                    public void onSuccess(ResponseObjects.LoginResponse loginResponseResult) {
                        super.onSuccess(loginResponseResult);
                        getMvpView().hideLoading();

                        if(loginResponseResult.success){
                            getMvpView().gotoMain();
                            getMvpView().closeEntranceActivities();
                            mUserHelper.onLogin(loginFields,loginResponseResult);
                        }else{
                            getMvpView().showServerMessage(loginResponseResult.message);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        getMvpView().hideLoading();
                        handleError(e,RC_LOGIN);
                    }
                });
    }

    @Override
    protected void onRetryClicked(int rqCode) {
        switch (rqCode){
            case RC_LOGIN :
                callLogin();
                break;
        }
    }
}
