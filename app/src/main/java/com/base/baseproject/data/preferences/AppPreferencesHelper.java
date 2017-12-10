package com.base.baseproject.data.preferences;

import android.content.Context;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */

public class AppPreferencesHelper extends PreferencesHelper{

    Context mContext;
    public AppPreferencesHelper(Context ctx){
        mContext = ctx;
    }

    @Override
    public void set(String key, String value) {
        PrefHandler.getInstance(mContext).set(key,value);
    }

    @Override
    public String get(String key) {
        return PrefHandler.getInstance(mContext).get(key);
    }

    @Override
    public String get(String key,String defValue) {
        return PrefHandler.getInstance(mContext).get(key,defValue);
    }

    @Override
    public void remove(String key) {
        PrefHandler.getInstance(mContext).remove(key);
    }

    @Override
    public boolean isExists(String key) {
        return PrefHandler.getInstance(mContext).isExists(key);
    }
}
