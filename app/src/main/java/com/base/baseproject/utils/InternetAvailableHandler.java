package com.base.baseproject.utils;

import java.util.ArrayList;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class InternetAvailableHandler {
    private static InternetAvailableHandler mInstance;
    public static InternetAvailableHandler getInstance(){
        if(mInstance==null)
            mInstance = new InternetAvailableHandler();
        return mInstance;
    }



    private ArrayList<InternetAvailableObserver> mObservers;
    public InternetAvailableHandler(){
        mObservers = new ArrayList<>();
    }

    public void addObserver(InternetAvailableObserver obs){
        mObservers.add(obs);
    }
    public void removeObserver(InternetAvailableObserver obs){
        mObservers.remove(obs);
    }
    private void notifyObservers(){
        for(InternetAvailableObserver uo : mObservers){
            uo.onInternetAvailable();
        }
    }

    public void onInternetAvailable(){
        notifyObservers();
    }

    public interface InternetAvailableObserver {
        void onInternetAvailable();
    }
 }
