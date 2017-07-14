package com.luseen.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.luseen.babylon.Babylon;

/**
 * Created by Chatikyan on 14.07.2017.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.text_view);
        textView.setText(getStringById(R.string.friendlyName));
    }

    private String getStringById(int id) {
        return Babylon.getStringById(this, id);
    }
}
