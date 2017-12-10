package com.base.baseproject.viewhelper.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.base.baseproject.R;
import com.base.baseproject.di.component.ActivityComponent;
import com.base.baseproject.listeners.OnRetryClickedListener;
import com.base.baseproject.mvp.base.BaseActivity;
import com.base.baseproject.viewhelper.SnackBarMaker;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public abstract class AppDialog extends Dialog {
    Context mContext;
    Snackbar snackbarInternetRetry;
    boolean isLoading = false;

    public AppDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    public AppDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected AppDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    public ActivityComponent getComponent() {
        if(!(mContext instanceof BaseActivity))return null;

        BaseActivity baseActivity = (BaseActivity) mContext;

        if (baseActivity != null) {
            return baseActivity.getComponent();
        }
        return null;
    }

    private void init(Context context) {
        mContext = context;
    }

    public abstract View getViewContainer();

    public void showMessageSnackShort(String message){
        if(message == null){
            showMessageSnackLong("null");
            return;
        }
        if(message.length()==0) {
            showMessageSnackLong("");
            return;
        }

        Snackbar snackbar;
        snackbar = Snackbar.make(getViewContainer(),message,Snackbar.LENGTH_SHORT);

        ((TextView)snackbar.getView().findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
        snackbar.show();
    }
    public void showMessageSnackLong(String message){
        if(message == null){
            showMessageSnackLong("null");
            return;
        }
        if(message.length()==0) {
            showMessageSnackLong("");
            return;
        }

        Snackbar snackbar;

        int duration = 5000;
        snackbar = Snackbar.make(getViewContainer(),message,duration);

        ((TextView)snackbar.getView().findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
        snackbar.show();
    }

    public void showMessageToastShort(String message){
        if(message == null){
            showMessageSnackLong("null");
            return;
        }
        if(message.length()==0) {
            showMessageSnackLong("");
            return;
        }

        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
    public void showMessageToastLong(String message){
        if(message == null){
            showMessageSnackLong("null");
            return;
        }
        if(message.length()==0) {
            showMessageSnackLong("");
            return;
        }

        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    public void showInternetRetry(View container, OnRetryClickedListener onRetryClickedListener){
        String networkProblemMessage = mContext.getString(R.string.error_network_connection_error);
        String retryStr = mContext.getResources().getString(R.string.retry);

        snackbarInternetRetry = SnackBarMaker.makeActionTextSnackbar(mContext, container,networkProblemMessage,retryStr,onRetryClickedListener);
        snackbarInternetRetry.show();
    }

    public void hideRetrySnackBar(){
        if(snackbarInternetRetry !=null && snackbarInternetRetry.isShown())
            snackbarInternetRetry.dismiss();
    }

}
