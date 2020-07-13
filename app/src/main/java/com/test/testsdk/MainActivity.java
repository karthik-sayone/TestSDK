package com.test.testsdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.test.mapviewjava.MapViewWrapper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapViewWrapper m = findViewById(R.id.map);

        m.initMapView(this,"tiles/esp/");
//        m.addNewMarker(this);
        m.addpath(this);
    }
}
