package com.base.baseproject.data.preferences;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public abstract class PreferencesHelper {
    public static final String KEY_IS_INTRO_PASSED = "isIntroPassed";

    public abstract void set(String key,String value);
    public abstract String get(String key);
    public abstract String get(String key,String defValue);
    public abstract void remove(String key);
    public abstract boolean isExists(String key);
}
