package com.example.jacosta.myapplication.view;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
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
import java.util.List;

/**
 * Created by jacosta on 12/28/16.
 * HomeScreen
 */

public class HomeScreen extends Screen {
    private static final String BUNDLE_RECYCLER_LAYOUT = "serialied_recycler_view";

    private HomeScreenBinding mBinding;
    private HomeScreenViewModel mViewModel;
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

        mViewModel = ViewModelProviders.of((LifecycleActivity) getContext()).get(HomeScreenViewModel.class);
        mViewModel.getStations().observe((LifecycleActivity) getContext(), setStations);
        mBinding.setViewModel(mViewModel);
    }

    @Override
    protected void onScreenDetached() {
        super.onScreenDetached();
        mViewModel.getStations().removeObserver(setStations);
    }

    @Override
    protected Bundle onSaveState(Bundle bundle) {
        bundle.putParcelable(BUNDLE_RECYCLER_LAYOUT, mBinding.stationList.getLayoutManager().onSaveInstanceState());
        return bundle;
    }

    @Override
    protected void onRestoreState(Bundle bundle) {
        if (bundle != null) {
            if (bundle.getParcelable(BUNDLE_RECYCLER_LAYOUT) != null){
                mSavedRecyclerLayoutState = bundle.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            }
        }
    }

    private Observer<ArrayList<SubwayStations.Station>> setStations = (Observer<ArrayList<SubwayStations.Station>>) stations ->
        mBinding.stationList.setAdapter(new StationAdapter(stations));

    @Override
    public int getViewId() {
        return R.id.home_screen;
    }
}
