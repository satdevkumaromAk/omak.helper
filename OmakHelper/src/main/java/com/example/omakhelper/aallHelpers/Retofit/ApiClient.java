package com.example.omakhelper.aallHelpers.Retofit;

import android.content.Context;


import com.example.omakhelper.R;
import com.example.omakhelper.aallHelpers.AlmightMainJSonModel;
import com.example.omakhelper.aallHelpers.PreferenceFile;
import com.example.omakhelper.aallHelpers.WebUrls;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String BASE_URL = "https://www.researchexperts.in/wp-json/rel/v1/";
    private static Retrofit retrofit = null;

    public Retrofit getClient() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    //.client(provideOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }

    private OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        return okhttpClientBuilder.build();
    }

    public AlmightMainJSonModel prepareMainJsModel(String taskType, Context context) {
        // ToDo: taskType introduced in this method to add different parameters from Preferences
        // for different API Requests
        PreferenceFile preferenceFile = new PreferenceFile();

        AlmightMainJSonModel task = new AlmightMainJSonModel();

        task.setReferrer(context.getString(R.string.referrer_id));
        task.setUdid(preferenceFile.getPreferenceData(context, WebUrls.ANDROID_ID));
        task.setApptoken(preferenceFile.getPreferenceData(context, WebUrls.APPTOKEN));
        task.setApp_version(preferenceFile.getPreferenceData(context, WebUrls.APP_VERSION));


        switch (taskType) {
            case "ap-public":
                break;

            case "ap-not-logged":
                break;

            case "ap-user":
                task.setClient_id(preferenceFile.getPreferenceData(context, WebUrls.USER_ID));
                task.setClient_role(preferenceFile.getPreferenceData(context, WebUrls.USER_ROLE));
                break;

            case "ap-admin":
                task.setRole("administrator");
                task.setClient_id(preferenceFile.getPreferenceData(context, WebUrls.USER_ID));
                task.setClient_role(preferenceFile.getPreferenceData(context, WebUrls.USER_ROLE));
        }

        return task;
    }
}