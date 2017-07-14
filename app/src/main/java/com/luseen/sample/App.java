package com.luseen.sample;

import android.app.Application;
import android.content.res.Configuration;

import com.luseen.babylon.Babylon;

/**
 * Created by Chatikyan on 14.07.2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Babylon.startInit(this)
                .fileUrl("https://armcha.github.io/strings.xml")
                .init();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Babylon.onConfigurationChanged(newConfig);
    }
}
