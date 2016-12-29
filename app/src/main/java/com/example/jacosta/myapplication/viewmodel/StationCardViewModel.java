package com.example.jacosta.myapplication.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.jacosta.myapplication.events.StationClickedEvent;
import com.example.jacosta.myapplication.model.SubwayStations;

import org.greenrobot.eventbus.EventBus;

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

    public void stationClicked(){
        EventBus.getDefault().post(new StationClickedEvent(mStation));
    }
}
