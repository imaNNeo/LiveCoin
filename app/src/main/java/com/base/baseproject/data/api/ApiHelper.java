package com.base.baseproject.data.api;

import com.base.baseproject.data.api.retrofit.models.RequestObjects;
import com.base.baseproject.data.api.retrofit.models.ResponseObjects;
import com.base.baseproject.data.db.room.entity.SampleItemEntity;

import java.util.ArrayList;
import io.reactivex.Single;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public abstract class ApiHelper {
    public abstract Single<ResponseObjects.LoginResponse> login(RequestObjects.LoginFields fields);
    public abstract Single<ArrayList<SampleItemEntity>> getSampleList();
}
