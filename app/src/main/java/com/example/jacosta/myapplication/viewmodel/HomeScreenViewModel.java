package com.example.jacosta.myapplication.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.example.jacosta.myapplication.databinding.HomeScreenBinding;
import com.example.jacosta.myapplication.events.LoadStationEvent;
import com.example.jacosta.myapplication.model.SubwayStations;
import com.example.jacosta.myapplication.view.adapter.StationAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by jacosta on 12/28/16.
 * ViewModel for my Home Screen
 */

public class HomeScreenViewModel extends BaseObservable {

    private ArrayList<SubwayStations.Station> mStations;
    private HomeScreenBinding mBinding;

    public HomeScreenViewModel(HomeScreenBinding binding){
        EventBus.getDefault().register(this);
        mBinding = binding;
        SubwayStations.loadStations();
    }

    public HomeScreenViewModel(ArrayList<SubwayStations.Station> stations, HomeScreenBinding binding){
        EventBus.getDefault().register(this);
        mBinding = binding;
        setStations(stations);
    }

    public ArrayList<SubwayStations.Station> getStations(){
        return mStations;
    }

    private void setStations(ArrayList<SubwayStations.Station> stations){
        mStations = stations;
        mBinding.stationList.setAdapter(new StationAdapter(mStations));
        notifyChange();
    }

    @Bindable
    public int getLoadingVisibility(){
        if (mStations == null){
            return View.VISIBLE;
        }
        return View.GONE;
    }

    @Bindable
    public int getStationsVisibility(){
        if (mStations == null){
            return View.GONE;
        }
        return View.VISIBLE;
    }

    @Subscribe
    public void onStationsLoaded(LoadStationEvent event){
        setStations(event.stations);
    }

    public void destroy() {
        EventBus.getDefault().unregister(this);
    }
}
