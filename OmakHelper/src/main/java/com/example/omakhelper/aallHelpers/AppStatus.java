package com.example.omakhelper.aallHelpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/*
Create by Rajeev 5/27/2019
This Class Checking Internet Connection
 */
public class AppStatus {

    static Context context;
    private static AppStatus instance = new AppStatus();
    ConnectivityManager connectivityManager;
    boolean connected = false;

    public static AppStatus getInstance(Context ctx) {
        context = ctx.getApplicationContext();
        return instance;
    }

    public boolean isOnline() {
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
            return connected;
        } catch (Exception e) {
            System.out.println("Check Connectivity Exception: " + e.getMessage());
            Log.v("Connectivity Error", e.toString());
        }
        return connected;
    }
}