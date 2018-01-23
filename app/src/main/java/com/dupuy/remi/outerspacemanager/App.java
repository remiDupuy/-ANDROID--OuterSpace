package com.dupuy.remi.outerspacemanager;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by lledent on 23/01/2018.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
