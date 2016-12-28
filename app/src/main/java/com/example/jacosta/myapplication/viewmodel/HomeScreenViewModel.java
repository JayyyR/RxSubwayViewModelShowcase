package com.example.jacosta.myapplication.viewmodel;

import android.databinding.BaseObservable;
import android.view.View;

import com.example.jacosta.myapplication.Events.LoadStationEvent;
import com.example.jacosta.myapplication.model.SubwayStations;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacosta on 12/28/16.
 * ViewModel for my Home Screen
 */

public class HomeScreenViewModel extends BaseObservable {

    private ArrayList<SubwayStations.Station> mStations;

    public HomeScreenViewModel(){
        EventBus.getDefault().register(this);
        SubwayStations.loadStations();
    }

    public HomeScreenViewModel(ArrayList<SubwayStations.Station> stations){
        EventBus.getDefault().register(this);
        setStations(stations);
    }

    public ArrayList<SubwayStations.Station> getStations(){
        return mStations;
    }

    private void setStations(ArrayList<SubwayStations.Station> stations){
        mStations = stations;
        notifyChange();
    }

    public int getLoadingVisibility(){
        if (mStations == null){
            return View.VISIBLE;
        }
        return View.GONE;
    }

    @Subscribe
    public void onStationsLoaded(LoadStationEvent event){
        setStations(event.stations);
    }

    public void destroy() {
        EventBus.getDefault().unregister(this);
    }
}
