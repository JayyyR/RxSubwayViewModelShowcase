package com.example.jacosta.myapplication.events;

import com.example.jacosta.myapplication.model.SubwayStations;

/**
 * Created by jacosta on 12/28/16.
 */

public class StationClickedEvent {

    public SubwayStations.Station station;

    public StationClickedEvent(SubwayStations.Station station) {
        this.station = station;
    }
}
