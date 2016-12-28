package com.example.jacosta.myapplication.network;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jacosta on 12/28/16.
 */

public interface SubwayAPI {

    @GET("/mta_esi.php?key={key}&feed_id=2")
    Call<JSONObject> getLInfo(@Path("key") String key);
}
