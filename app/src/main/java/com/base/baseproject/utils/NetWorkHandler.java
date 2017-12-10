package com.base.baseproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class NetWorkHandler {
    public static boolean isNetworkThrowable(Throwable t){
        return (t instanceof ConnectException ||
                t instanceof SocketTimeoutException ||
                t instanceof UnknownHostException);
    }

    public static boolean isOnline(Context ctx) {
        ConnectivityManager cm =
                (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
