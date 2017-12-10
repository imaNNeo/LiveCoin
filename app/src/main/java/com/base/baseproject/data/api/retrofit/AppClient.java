package com.base.baseproject.data.api.retrofit;

import com.base.baseproject.data.api.retrofit.models.ResponseObjects;
import com.base.baseproject.data.db.room.entity.SampleItemEntity;
import com.base.baseproject.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Amir Abdi on 01/05/2017.
 */

public interface AppClient {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST(AppConstants.LOGIN_PATH)
    Single<ResponseObjects.LoginResponse> login(@Field("email") String email, @Field("password") String password);


    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST(AppConstants.GET_ITEMS)
    Single<ArrayList<SampleItemEntity>> getSampleItems(@Field("token") String token);



    @Headers("Accept: application/json")
    @GET(AppConstants.GET_COIN_ITEMS)
    Single<List<ResponseObjects.CoinItem>> getCoinItems(@Query("limit") int limit);

}