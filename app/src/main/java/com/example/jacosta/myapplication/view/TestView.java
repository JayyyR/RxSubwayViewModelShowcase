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
import android.view.View;

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

public class TestView extends Screen {
    private static final String BUNDLE_RECYCLER_LAYOUT = "serialied_recycler_view";

    private HomeScreenBinding mBinding;
    private HomeScreenViewModel mViewModel;
    private Parcelable mSavedRecyclerLayoutState;

    public static class Factory extends ViewFactory {
        @Override
        public int getLayoutResource() {
            return R.layout.test_screen;
        }
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScreenAttached() {
        super.onScreenAttached();

        findViewById(R.id.test_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ContainerActivity)getContext()).pushHomeScreen();
            }
        });
    }

    @Override
    public int getViewId() {
        return 3213;
    }
}
