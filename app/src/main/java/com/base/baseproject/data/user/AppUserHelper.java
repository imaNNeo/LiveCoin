package com.base.baseproject.data.user;

import android.content.Context;

import com.base.baseproject.data.api.retrofit.models.RequestObjects;
import com.base.baseproject.data.api.retrofit.models.ResponseObjects;
import com.base.baseproject.rx.bus.RxBus;
import com.base.baseproject.rx.bus.events.UserChangeEvent;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */

public class AppUserHelper extends UserHelper{

    Context mContext;
    UserHandler mUserHandler;
    public AppUserHelper(Context ctx,
                         UserHandler uh){
        ctx = ctx.getApplicationContext();
        mContext = ctx;
        mUserHandler = uh;
    }

    @Override
    public void onLogin(RequestObjects.LoginFields fields, ResponseObjects.LoginResponse response) {
        mUserHandler.onLogin(fields,response);
        RxBus.getInstance().send(new UserChangeEvent());
    }

    @Override
    public boolean isLogin() {
        return mUserHandler.isLogin();
    }

    @Override
    public void logoutUser() {
        mUserHandler.logoutUser();
        RxBus.getInstance().send(new UserChangeEvent());
    }

    @Override
    public String getToken() {
        return mUserHandler.getToken();
    }

    @Override
    public String getUsername() {
        return mUserHandler.getUsername();
    }

    @Override
    public String getPassword() {
        return mUserHandler.getPassword();
    }

}
