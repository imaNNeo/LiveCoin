package com.base.baseproject.data.api.retrofit;

import com.base.baseproject.data.api.retrofit.models.RequestObjects;
import com.base.baseproject.data.api.retrofit.models.ResponseObjects;
import com.base.baseproject.data.db.room.entity.SampleItemEntity;
import com.base.baseproject.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class RetrofitApiHandler {

    private static RetrofitApiHandler mInstance;
    public static RetrofitApiHandler getInstance(){
        if(mInstance==null)
            mInstance = new RetrofitApiHandler();
        return mInstance;
    }


//    private Context mContext;
    private AppClient appClient;
    private RetrofitApiHandler(){
        appClient = ServiceGenerator.createService(AppClient.class, AppConstants.API_BASE_URL);
    }

    public Single<ResponseObjects.LoginResponse> login(RequestObjects.LoginFields fields){
        return appClient.login(fields.getUsername(), fields.getPassword());
    }
    public Single<ArrayList<SampleItemEntity>> getSampleList(String token){
        return appClient.getSampleItems(token);
    }
    public Single<List<ResponseObjects.CoinItem>> getCoinItems(RequestObjects.GetCoinItemsFields fields){
        return appClient.getCoinItems(fields.getLimit());
    }

}