package com.example.jacosta.myapplication.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jacosta.myapplication.R;
import com.example.jacosta.myapplication.databinding.PageHomeBinding;
import com.example.jacosta.myapplication.model.SubwayStations;
import com.example.jacosta.myapplication.viewmodel.HomeScreenViewModel;
import com.joeracosta.library.Stack.ViewStack;
import com.joeracosta.library.Stack.ViewStackDelegate;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements ViewStackDelegate {

    private static final String STATIONS_KEY = "seralized_stations";

    private ViewStack mStack;
    private PageHomeBinding mBinding;
    private HomeScreenViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.page_home, null, false);
        setContentView(mBinding.getRoot());

        mBinding.stationList.setLayoutManager(new LinearLayoutManager(this));

        //restore state
        if (savedInstanceState != null
                && savedInstanceState.getSerializable(STATIONS_KEY) != null){
            mViewModel = new HomeScreenViewModel((ArrayList<SubwayStations.Station>) savedInstanceState.getSerializable(STATIONS_KEY), mBinding);
        } else {
            mViewModel = new HomeScreenViewModel(mBinding);
        }


        mBinding.setViewModel(mViewModel);
        mStack = ViewStack.create((ViewGroup)findViewById(R.id.app_content), this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putSerializable(STATIONS_KEY, mViewModel.getStations());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void finishStack() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.destroy();
    }
}
