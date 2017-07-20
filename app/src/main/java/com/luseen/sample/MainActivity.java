package com.luseen.sample;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.luseen.babylon.Babylon;
import com.luseen.babylon.BabylonContextWrapper;
import com.luseen.babylon.StringReadyListener;
import com.luseen.logger.Logger;

import java.util.Locale;
import java.util.Random;

/**
 * Created by Chatikyan on 14.07.2017.
 */

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.text_view);
        textView.setText(R.string.friendlyName);

        Babylon.setStringReadyListener(new StringReadyListener() {
            @Override
            public void onResourceReady() {
                Logger.log("onResourceReady");
                textView.setText(R.string.friendlyName);
                //button.setText(R.string.dummy);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Locale locale = new Locale(getRandomLocale());
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getResources().updateConfiguration(config, getResources().getDisplayMetrics());
                Babylon.setCurrentLocale(MainActivity.this, locale);

                Logger.log(Locale.getDefault());
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(BabylonContextWrapper.wrap(newBase));
    }

    private String getRandomLocale() {
        String[] localeList = {"ru", "en", "hy"};
        Random random = new Random();
        return localeList[random.nextInt(localeList.length)];
    }
}
