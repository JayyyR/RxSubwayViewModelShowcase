package com.example.jacosta.myapplication.network;

import retrofit2.Retrofit;

/**
 * Created by jacosta on 12/28/16.
 */

public class RetrofitInstance {

    private static final String BASE_URL = "http://datamine.mta.info";

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
                    .build();
        }

        return mRetroFit;
    }

}
