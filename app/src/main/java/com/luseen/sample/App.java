package com.luseen.sample;

import android.app.Application;
import android.content.res.Configuration;

import com.luseen.babylon.Babylon;
import com.luseen.logger.LogType;
import com.luseen.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Chatikyan on 14.07.2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        Babylon.startInit(this)
                .fileUrl("https://armcha.github.io/strings.xml")
                .init();

        new Logger.Builder()
                .isLoggable(true)
                .logType(LogType.ERROR)
                .tag("Some Tag")
                .build();

    }
}
