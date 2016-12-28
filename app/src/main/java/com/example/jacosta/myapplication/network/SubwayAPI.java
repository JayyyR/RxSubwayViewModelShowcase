package com.example.jacosta.myapplication.network;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jacosta on 12/28/16.
 */

public interface SubwayAPI {

    @GET("/stations")
    Call<Object> getStations();
}
