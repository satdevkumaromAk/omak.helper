package com.example.omakandroidlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.omakhelper.HelperFunctions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HelperFunctions.log("MainData");
    }
}