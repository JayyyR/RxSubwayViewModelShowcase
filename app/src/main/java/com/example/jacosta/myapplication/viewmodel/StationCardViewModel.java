package com.example.jacosta.myapplication.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.jacosta.myapplication.model.SubwayStations;

/**
 * Created by jacosta on 12/28/16.
 */

public class StationCardViewModel extends BaseObservable {

    private SubwayStations.Station mStation;

    public void setStation(SubwayStations.Station station){
        mStation = station;
        notifyChange();
    }

    @Bindable
    public String getStationName(){
        return mStation != null ? mStation.getName() : null;
    }
}
