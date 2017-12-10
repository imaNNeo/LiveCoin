package com.base.baseproject.data.api;

import android.content.Context;

import com.base.baseproject.data.api.retrofit.RetrofitApiHandler;
import com.base.baseproject.data.api.retrofit.models.RequestObjects;
import com.base.baseproject.data.api.retrofit.models.ResponseObjects;
import com.base.baseproject.data.db.room.entity.SampleItemEntity;
import com.base.baseproject.data.user.UserHandler;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */

public class AppApiHelper extends ApiHelper {

    Context mContext;
    UserHandler mUserHandler;
    public AppApiHelper(Context ctx,
                        UserHandler uh){
        ctx = ctx.getApplicationContext();
        mContext = ctx;
        mUserHandler = uh;
    }

    @Override
    public Single<ResponseObjects.LoginResponse> login(RequestObjects.LoginFields fields) {
        return RetrofitApiHandler.getInstance().login(fields);
    }

    @Override
    public Single<ArrayList<SampleItemEntity>> getSampleList() {
        String token = mUserHandler.getToken();
        return RetrofitApiHandler.getInstance().getSampleList(token);
    }

    @Override
    public Single<List<ResponseObjects.CoinItem>> getCoinItems(RequestObjects.GetCoinItemsFields fields) {
        return RetrofitApiHandler.getInstance().getCoinItems(fields);
    }


    public static class MySingleObserver<T> implements SingleObserver<T> {
        @Override
        public void onSubscribe(Disposable d){
        }

        @Override
        public void onSuccess(T t){
        }

        @Override
        public void onError(Throwable e){
        }
    }
}
