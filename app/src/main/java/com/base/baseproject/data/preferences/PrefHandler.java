package com.base.baseproject.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.securepreferences.SecurePreferences;

/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class PrefHandler {
    public static final String KEY_USER_USERNAME = "userName";
    public static final String KEY_USER_PASSWORD = "password";
    public static final String KEY_USER_ACCESS_TOKEN = "accessToken";


    private static PrefHandler mPrefHandler;
    public static PrefHandler getInstance(Context ctx){
        ctx = ctx.getApplicationContext();
        if(mPrefHandler==null)
            mPrefHandler = new PrefHandler(ctx);

        return mPrefHandler;
    }

    Context mContext;
    SharedPreferences mSharedPreferences;
    private PrefHandler(Context ctx){
        mContext = ctx;
        mSharedPreferences = new SecurePreferences(mContext);
    }

    public void set(String key,String value){
        mSharedPreferences
                .edit()
                .putString(key,value)
                .commit();
    }
    public String get(String key){
        return get(key,"");
    }
    public String get(String key,String value){
        return mSharedPreferences.getString(key,value);
    }
    public void remove(String key){
        mSharedPreferences.edit().remove(key).commit();
    }
    public boolean isExists(String key){
        return !TextUtils.isEmpty(get(key));
    }
}