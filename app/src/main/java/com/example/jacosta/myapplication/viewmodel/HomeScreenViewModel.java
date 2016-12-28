package com.example.jacosta.myapplication.viewmodel;

import android.databinding.BaseObservable;

import com.example.jacosta.myapplication.network.InterwebzLoader;
import com.example.jacosta.myapplication.persistent.App;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jacosta on 12/28/16.
 * ViewModel for my Home Screen
 */

public class HomeScreenViewModel extends BaseObservable {

    public void isTheLFucked(){
        InterwebzLoader.getSubwayAPI().getStations(App.KEY).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                System.out.println();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                System.out.println();
            }
        });
    }
}
