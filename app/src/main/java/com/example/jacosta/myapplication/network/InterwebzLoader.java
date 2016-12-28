package com.example.jacosta.myapplication.network;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jacosta on 12/28/16.
 */

public class InterwebzLoader {

    private static final String BASE_URL = "http://mtaapi.herokuapp.com";

    private static SubwayAPI mSubwayAPI;
    private static Retrofit mRetroFit;

    public static SubwayAPI getSubwayAPI(){

        if (mSubwayAPI == null){
            mSubwayAPI = getRetroFit().create(SubwayAPI.class);
        }

        return mSubwayAPI;
    }

    private static Retrofit getRetroFit(){
        if (mRetroFit == null){
            mRetroFit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }

        return mRetroFit;
    }

}
