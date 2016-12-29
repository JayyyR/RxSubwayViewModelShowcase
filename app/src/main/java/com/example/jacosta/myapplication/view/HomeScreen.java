package com.example.jacosta.myapplication.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

import com.example.jacosta.myapplication.R;
import com.example.jacosta.myapplication.databinding.HomeScreenBinding;
import com.example.jacosta.myapplication.model.SubwayStations;
import com.example.jacosta.myapplication.viewmodel.HomeScreenViewModel;
import com.joeracosta.library.Screen;
import com.joeracosta.library.ViewFactory;

import java.util.ArrayList;

/**
 * Created by jacosta on 12/28/16.
 * HomeScreen
 */

public class HomeScreen extends Screen {

    private static final String STATIONS_KEY = "seralized_stations";

    private HomeScreenBinding mBinding;
    private HomeScreenViewModel mViewModel;
    private ArrayList<SubwayStations.Station> mStations;

    public static class Factory extends ViewFactory {

        @Override
        public int getLayoutResource() {
            return R.layout.home_screen;
        }
    }

    public HomeScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScreenAttached() {
        super.onScreenAttached();
        mBinding = DataBindingUtil.bind(this);

        mBinding.stationList.setLayoutManager(new LinearLayoutManager(getContext()));

        //restore state
        if (mStations == null) {
            mViewModel = new HomeScreenViewModel(mBinding);
        } else {
            mViewModel = new HomeScreenViewModel(mStations, mBinding);
        }

        mBinding.setViewModel(mViewModel);
    }

    @Override
    protected void onScreenDetached() {
        super.onScreenDetached();

        mViewModel.destroy();
    }

    @Override
    protected Bundle onSaveState(Bundle bundle) {

        bundle.putSerializable(STATIONS_KEY, mViewModel.getStations());
        return bundle;
    }

    @Override
    protected void onRestoreState(Bundle bundle) {
        if (bundle != null && bundle.getSerializable(STATIONS_KEY) != null) {
            mStations = (ArrayList<SubwayStations.Station>) bundle.getSerializable(STATIONS_KEY);
        }
    }

    @Override
    public int getViewId() {
        return R.id.home_screen;
    }
}
