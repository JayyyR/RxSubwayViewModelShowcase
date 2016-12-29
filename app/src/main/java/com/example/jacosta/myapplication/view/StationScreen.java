package com.example.jacosta.myapplication.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.example.jacosta.myapplication.R;
import com.example.jacosta.myapplication.databinding.StationScreenBinding;
import com.example.jacosta.myapplication.model.SubwayStations;
import com.example.jacosta.myapplication.viewmodel.StationScreenViewModel;
import com.joeracosta.library.Screen;
import com.joeracosta.library.ViewFactory;

/**
 * Created by jacosta on 12/28/16.
 */

public class StationScreen extends Screen {

    public static final String PASSED_STATION_KEY = "passed_station_key";

    private StationScreenViewModel mStationScreenViewModel;
    private StationScreenBinding mBinding;
    private SubwayStations.Station mStation;

    public static class Factory extends ViewFactory {
        @Override
        public int getLayoutResource() {
            return R.layout.station_screen;
        }
    }

    public StationScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScreenAttached() {
        super.onScreenAttached();

        mBinding = DataBindingUtil.bind(this);
        Bundle data = getPassedData();

        if (mStation == null && data != null && data.containsKey(PASSED_STATION_KEY)){
            mStation = (SubwayStations.Station) data.getSerializable(PASSED_STATION_KEY);
        }

        if (mStation == null){
            throw new RuntimeException("Must pass a station");
        }

        mStationScreenViewModel = new StationScreenViewModel(mStation);
        mBinding.setViewModel(mStationScreenViewModel);
    }

    @Override
    protected void onScreenDetached() {
        super.onScreenDetached();
    }

    @Override
    protected Bundle onSaveState(Bundle bundle) {
        bundle.putSerializable(PASSED_STATION_KEY, mStationScreenViewModel.getStation());
        return bundle;
    }

    @Override
    protected void onRestoreState(Bundle bundle) {
        super.onRestoreState(bundle);
        if (bundle != null && bundle.containsKey(PASSED_STATION_KEY)){
            mStation = (SubwayStations.Station) bundle.getSerializable(PASSED_STATION_KEY);
        }
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
    }

    @Override
    protected void onScreenGone() {
        super.onScreenGone();
    }

    @Override
    public int getViewId() {
        return R.id.station_screen;
    }
}
