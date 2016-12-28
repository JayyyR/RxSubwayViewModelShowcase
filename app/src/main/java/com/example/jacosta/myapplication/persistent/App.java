package com.example.jacosta.myapplication.persistent;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by jacosta on 12/28/16.
 */

public class App extends Application {

    public static final String KEY = "79f593bdb89bd0ed8e757604961fe53b";

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
