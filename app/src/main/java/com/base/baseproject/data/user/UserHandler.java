package com.base.baseproject.data.user;

import android.text.TextUtils;

import com.base.baseproject.data.api.retrofit.models.RequestObjects;
import com.base.baseproject.data.api.retrofit.models.ResponseObjects;
import com.base.baseproject.data.preferences.PrefHandler;
import com.base.baseproject.data.preferences.PreferencesHelper;

/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class UserHandler{
    private PreferencesHelper mPrefHelpter;
    public UserHandler(PreferencesHelper pref){
        this.mPrefHelpter = pref;
    }

    public void onLogin(RequestObjects.LoginFields fields,
                        ResponseObjects.LoginResponse response){
        mPrefHelpter.set(PrefHandler.KEY_USER_USERNAME, fields.getUsername());
        mPrefHelpter.set(PrefHandler.KEY_USER_PASSWORD, fields.getPassword());
        mPrefHelpter.set(PrefHandler.KEY_USER_ACCESS_TOKEN,response.token);
    }
    public boolean isLogin(){
        return !TextUtils.isEmpty(getToken());
    }
    public void logoutUser(){
        mPrefHelpter.remove(PrefHandler.KEY_USER_USERNAME);
        mPrefHelpter.remove(PrefHandler.KEY_USER_PASSWORD);
        mPrefHelpter.remove(PrefHandler.KEY_USER_ACCESS_TOKEN);
    }
    public String getToken(){
        return mPrefHelpter.get(PrefHandler.KEY_USER_ACCESS_TOKEN);
    }
    public String getUsername(){
        return mPrefHelpter.get(PrefHandler.KEY_USER_USERNAME);
    }
    public String getPassword(){
        return mPrefHelpter.get(PrefHandler.KEY_USER_PASSWORD);
    }


}