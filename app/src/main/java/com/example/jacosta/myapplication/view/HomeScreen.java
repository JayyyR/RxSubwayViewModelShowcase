package com.example.jacosta.myapplication.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

import com.example.jacosta.myapplication.R;
import com.example.jacosta.myapplication.databinding.HomeScreenBinding;
import com.example.jacosta.myapplication.model.SubwayStations;
import com.example.jacosta.myapplication.view.adapter.StationAdapter;
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
    private static final String BUNDLE_RECYCLER_LAYOUT = "serialied_recycler_view";

    private HomeScreenBinding mBinding;
    private HomeScreenViewModel mViewModel;
    private ArrayList<SubwayStations.Station> mStations;
    private Parcelable mSavedRecyclerLayoutState;

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
        //recyclerview position
        if (mSavedRecyclerLayoutState != null){
            mBinding.stationList.getLayoutManager().onRestoreInstanceState(mSavedRecyclerLayoutState);
            mSavedRecyclerLayoutState = null;
        }


        mViewModel = ViewModelProviders.of((FragmentActivity) getContext()).get(HomeScreenViewModel.class);
        mViewModel.getStationsObservable().subscribe(stations -> {
            mBinding.stationList.setAdapter(new StationAdapter(stations));
        });
        mBinding.setViewModel(mViewModel);
    }

    @Override
    protected void onScreenDetached() {
        super.onScreenDetached();

        mViewModel.destroy();
    }

    @Override
    protected Bundle onSaveState(Bundle bundle) {
        bundle.putParcelable(BUNDLE_RECYCLER_LAYOUT, mBinding.stationList.getLayoutManager().onSaveInstanceState());
        return bundle;
    }

    @Override
    protected void onRestoreState(Bundle bundle) {
        if (bundle != null) {
            if (bundle.getSerializable(STATIONS_KEY) != null) {
                mStations = (ArrayList<SubwayStations.Station>) bundle.getSerializable(STATIONS_KEY);
            }

            if (bundle.getParcelable(BUNDLE_RECYCLER_LAYOUT) != null){
                mSavedRecyclerLayoutState = bundle.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            }
        }
    }

    @Override
    public int getViewId() {
        return R.id.home_screen;
    }
}
