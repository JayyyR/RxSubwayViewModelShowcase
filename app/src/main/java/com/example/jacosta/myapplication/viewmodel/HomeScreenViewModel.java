package com.example.jacosta.myapplication.viewmodel;

import android.databinding.BaseObservable;

import com.example.jacosta.myapplication.network.InterwebzLoader;
import com.example.jacosta.myapplication.persistent.App;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jacosta on 12/28/16.
 */

public class HomeScreenViewModel extends BaseObservable {

    public void onButtonClicked(){
        InterwebzLoader.getSubwayAPI().getLInfo(App.KEY).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                System.out.println();
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                System.out.println();
            }
        });
    }
}
