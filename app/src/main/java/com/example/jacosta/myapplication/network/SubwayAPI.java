package com.example.jacosta.myapplication.network;

import com.example.jacosta.myapplication.model.SubwayStations;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by jacosta on 12/28/16.
 */

public interface SubwayAPI {

    @GET("/stations")
    Observable<SubwayStations> getStations();
}
