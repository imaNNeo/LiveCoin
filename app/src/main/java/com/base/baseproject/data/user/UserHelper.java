package com.base.baseproject.data.user;

import com.base.baseproject.data.api.retrofit.models.RequestObjects;
import com.base.baseproject.data.api.retrofit.models.ResponseObjects;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public abstract class UserHelper {
    public abstract void onLogin(RequestObjects.LoginFields fields,
                                 ResponseObjects.LoginResponse response);
    public abstract boolean isLogin();
    public abstract void logoutUser();
    public abstract String getToken();
    public abstract String getUsername();
    public abstract String getPassword();
}
