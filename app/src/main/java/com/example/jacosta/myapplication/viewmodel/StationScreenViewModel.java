package com.example.jacosta.myapplication.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.jacosta.myapplication.model.SubwayStations;

/**
 * Created by jacosta on 12/28/16.
 */

public class StationScreenViewModel extends BaseObservable {

    private SubwayStations.Station mStation;

    public StationScreenViewModel(SubwayStations.Station station){
        mStation = station;
    }

    public SubwayStations.Station getStation(){
        return mStation;
    }

    @Bindable
    public String getStationName(){
        return mStation.getName();
    }

}
