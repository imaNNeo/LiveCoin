package com.base.baseproject.mvp.base.loading;

import android.support.annotation.IntDef;

import com.base.baseproject.data.api.retrofit.models.ResponseObjects;
import com.base.baseproject.listeners.OnActionClickedListener;
import com.base.baseproject.mvp.base.BaseMvpPresenter;
import com.base.baseproject.utils.InternetAvailableHandler;
import com.base.baseproject.utils.NetWorkHandler;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashSet;
import java.util.Set;

import okhttp3.ResponseBody;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public abstract class LoadingMvpPresenter<V extends LoadingMvpView> extends BaseMvpPresenter<V> implements InternetAvailableHandler.InternetAvailableObserver {

    public static final byte ECT_INTERNET = 1;
    public static final byte ECT_SYSTEM = 2;
    @IntDef({ECT_INTERNET, ECT_SYSTEM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorCodeType{}

    public Set<Byte> failedInternetRequests;

    public boolean isLoading = false;

    public LoadingMvpPresenter() {
        super();
        failedInternetRequests = new HashSet<>();
        InternetAvailableHandler.getInstance().addObserver(this);
    }

    @ErrorCodeType
    public int handleError(Throwable t,final byte rqCode){
        @ErrorCodeType
        int errorCodeType = checkErrorType(t,rqCode);

        if(errorCodeType==ECT_SYSTEM){
            failedInternetRequests.remove(rqCode);
        }

        return errorCodeType;
    }

    @ErrorCodeType
    private int checkErrorType(Throwable t,final int rqCode){

        OnActionClickedListener errorRetryListener = () -> {
            getMvpView().hideSystemError();
            onRetryClicked(rqCode);
        };

        OnActionClickedListener internetRetryListener = () -> {
            getMvpView().hideInternetRetry();
            onRetryClicked(rqCode);
        };
        if(t==null) {
            getMvpView().showSystemError(null,errorRetryListener);
            return ECT_SYSTEM;
        }


        if(t instanceof HttpException){
            ResponseBody body = ((HttpException) t).response().errorBody();
            String bodyStr;
            try {
                bodyStr = body.string().toString();
            } catch (IOException e) {
                getMvpView().showSystemError(null,errorRetryListener);
                return ECT_SYSTEM;
            }
            ResponseObjects.ErrorAnswer errorAnswer = null;
            try {
                errorAnswer = new Gson().fromJson(bodyStr, ResponseObjects.ErrorAnswer.class);
            } catch (JsonSyntaxException e) {
                getMvpView().showSystemError(null,errorRetryListener);
            }
            if(errorAnswer!=null)
                getMvpView().showSystemError(errorAnswer.error + "\n" + errorAnswer.error_description,errorRetryListener);
            return ECT_SYSTEM;
        }else if(NetWorkHandler.isNetworkThrowable(t)){
            getMvpView().showInternetRetry(internetRetryListener);
            return ECT_INTERNET;
        }else{
            getMvpView().showSystemError(null,errorRetryListener);
            return ECT_SYSTEM;
        }
    }

    public void handleAnswer(){

    }
    protected abstract void onRetryClicked(int rqCode);

    @Override
    public void onInternetAvailable() {
        if(!isViewAttached())return;

        getMvpView().hideInternetRetry();
    }
}