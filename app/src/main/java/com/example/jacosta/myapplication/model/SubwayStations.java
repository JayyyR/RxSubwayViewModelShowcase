package com.example.jacosta.myapplication.model;

import com.example.jacosta.myapplication.events.LoadStationEvent;
import com.example.jacosta.myapplication.network.InterwebzLoader;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jacosta on 12/28/16.
 * Model object for loading SubwayStations
 */

public class SubwayStations implements Serializable {

    @SerializedName("result")
    public ArrayList<Station> result;

    public static void loadStations() {
        final HashMap<String, Station> stationMap = new HashMap<>();
        InterwebzLoader.getSubwayAPI().getStations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(subwayStations -> Observable.from(subwayStations.result))
                .subscribe(
                        station -> {
                            stationMap.put(station.name, station); //only grab unique stations
                        },
                        throwable -> {
                        },
                        () -> {
                            ArrayList<Station> stations = new ArrayList<>(stationMap.values());
                            Collections.sort(stations, new Station.StationComparator());
                            EventBus.getDefault().post(new LoadStationEvent(stations));
                        }
                );
    }

    public static class Station implements Serializable {

        @SerializedName("id")
        private String id;

        @SerializedName("name")
        private String name;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        static class StationComparator implements Comparator<Station> {
            public int compare(Station c1, Station c2) {
                return c1.getName().compareTo(c2.getName());
            }
        }

    }
}
