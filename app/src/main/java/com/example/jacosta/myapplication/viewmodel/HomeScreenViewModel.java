package com.example.jacosta.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.Bindable;
import android.view.View;

import com.example.jacosta.myapplication.BaseObservableViewModel;
import com.example.jacosta.myapplication.model.SubwayStations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by jacosta on 12/28/16.
 * ViewModel for my Home Screen
 */

public class HomeScreenViewModel extends BaseObservableViewModel {

    private MutableLiveData<ArrayList<SubwayStations.Station>> mStations;
    private boolean isLoaded;

    public LiveData<ArrayList<SubwayStations.Station>> getStations() {
        if (mStations == null) {
            mStations = new MutableLiveData<>();
            loadStations();
        }
        return mStations;

    }

    private void loadStations() {
        final HashMap<String, SubwayStations.Station> stationMap = new HashMap<>();
        SubwayStations.loadStations().subscribe(
                station -> {
                    stationMap.put(station.getName(), station); //only grab unique stations
                },
                throwable -> {
                },
                () -> {
                    ArrayList<SubwayStations.Station> stations = new ArrayList<>(stationMap.values());
                    Collections.sort(stations, new SubwayStations.Station.StationComparator());
                    mStations.setValue(stations);
                    isLoaded = true;
                    notifyChange();
                }
        );

    }

    @Bindable
    public int getLoadingVisibility() {
        if (!isLoaded) {
            return View.VISIBLE;
        }
        return View.GONE;
    }

    @Bindable
    public int getStationsVisibility() {
        if (!isLoaded) {
            return View.GONE;
        }
        return View.VISIBLE;
    }
}
