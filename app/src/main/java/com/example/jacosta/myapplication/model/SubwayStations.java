package com.example.jacosta.myapplication.model;

import com.example.jacosta.myapplication.Events.LoadStationEvent;
import com.example.jacosta.myapplication.network.InterwebzLoader;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jacosta on 12/28/16.
 */

public class SubwayStations implements Serializable{

    @SerializedName("result")
    public ArrayList<Station> result;

    public static void loadStations() {
        InterwebzLoader.getSubwayAPI().getStations().enqueue(new Callback<SubwayStations>() {
            @Override
            public void onResponse(Call<SubwayStations> call, Response<SubwayStations> response) {
                EventBus.getDefault().post(new LoadStationEvent(response.body().result));
            }

            @Override
            public void onFailure(Call<SubwayStations> call, Throwable t) {
                System.out.println();
            }
        });
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

    }
}
