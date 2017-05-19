package com.example.jacosta.myapplication.viewmodel;

import android.databinding.Bindable;
import android.view.View;

import com.example.jacosta.myapplication.BaseObservableViewModel;
import com.example.jacosta.myapplication.model.SubwayStations;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by jacosta on 12/28/16.
 * ViewModel for my Home Screen
 */

public class HomeScreenViewModel extends BaseObservableViewModel {

    private Observable<ArrayList<SubwayStations.Station>> mStationsObservable;
    private ArrayList<SubwayStations.Station> mStations;

    public Observable<ArrayList<SubwayStations.Station>> getStationsObservable(){

        if (mStationsObservable == null){
            mStationsObservable = getStationsObserver();
        }
        return mStationsObservable;

    }

    private Observable<ArrayList<SubwayStations.Station>> getStationsObserver(){

        return Observable.create(subscriber -> {
            if (mStations != null){
                subscriber.onNext(mStations);
            } else {
                final HashMap<String, SubwayStations.Station> stationMap = new HashMap<>();
                SubwayStations.loadStations().subscribe(
                        station -> {
                            stationMap.put(station.getName(), station); //only grab unique stations
                        },
                        throwable -> {
                        },
                        () -> {
                            mStations = new ArrayList<>(stationMap.values());
                            Collections.sort(mStations, new SubwayStations.Station.StationComparator());
                            subscriber.onNext(mStations); //emit from our other observable
                            notifyChange();

                        }
                );
            }
        });

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

    public void destroy() {
        EventBus.getDefault().unregister(this);
    }
}
