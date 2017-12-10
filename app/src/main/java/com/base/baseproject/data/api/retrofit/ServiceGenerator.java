package com.base.baseproject.data.api.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Amir Abdi on 01/05/2017.
 */

public class ServiceGenerator {

    private static boolean debug = true;

/*    public ServiceGenerator() {
    }*/

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.retryOnConnectionFailure(true).connectTimeout(120L, TimeUnit.SECONDS);
        if (debug) {
            httpClient.networkInterceptors().add(logger);
        }

        OkHttpClient client = httpClient.build();
        Gson gson = (new GsonBuilder()).setLenient().create();
        retrofit2.Retrofit.Builder builder =
                (new retrofit2.Retrofit.Builder())
                        .baseUrl(baseUrl)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}

