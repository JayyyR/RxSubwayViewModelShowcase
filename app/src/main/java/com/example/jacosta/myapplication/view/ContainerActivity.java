package com.example.jacosta.myapplication.view;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import com.example.jacosta.myapplication.R;
import com.example.jacosta.myapplication.events.StationClickedEvent;
import com.joeracosta.library.Stack.ViewStack;
import com.joeracosta.library.Stack.ViewStackDelegate;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class ContainerActivity extends FragmentActivity implements ViewStackDelegate {


    private static final String STACK_TAG = "viewstack_key";
    private ViewStack mStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_activity);
        EventBus.getDefault().register(this);

        mStack = ViewStack.create((ViewGroup)findViewById(R.id.app_content), this);

        if (savedInstanceState != null) {
            mStack.rebuildFromBundle(savedInstanceState, STACK_TAG);
        } else {
            mStack.push(new HomeScreen.Factory());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mStack.saveToBundle(outState, STACK_TAG);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void finishStack() {
        finish();
    }

    @Subscribe
    public void onStationClicked(StationClickedEvent event){
        StationScreen.Factory factory = new StationScreen.Factory();
        Bundle data = new Bundle();
        data.putSerializable(StationScreen.PASSED_STATION_KEY, event.station);
        factory.passData(data);
        mStack.push(factory);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        boolean handled = mStack.onBackPressed();

        if (!handled){
            super.onBackPressed();
        }
    }
}
