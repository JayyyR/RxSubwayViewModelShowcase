package com.example.jacosta.myapplication.events;

import com.example.jacosta.myapplication.model.SubwayStations;

import java.util.ArrayList;

/**
 * Created by jacosta on 12/28/16.
 */

public class LoadStationEvent {
    public ArrayList<SubwayStations.Station> stations;

    public LoadStationEvent(ArrayList<SubwayStations.Station> stations) {
        this.stations = stations;
    }

}
