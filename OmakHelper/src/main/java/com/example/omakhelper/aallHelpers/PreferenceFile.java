package com.example.omakhelper.aallHelpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceFile {
    public static SharedPreferences.Editor aap_lang_editor;
    private static SharedPreferences sharedPreference_appLang;
    private static PreferenceFile instance = null;
    private SharedPreferences sharedPreferences;

    public PreferenceFile() {
        super();
        sharedPreferences = App.getGlobalPrefs();
    }

    public static PreferenceFile getInstance() {
        if (instance != null) {
            return instance;
        } else {
            return instance = new PreferenceFile();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    public static SharedPreferences getSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ijuser-pref", MODE_PRIVATE);
        return sharedPreferences;
    }

    //  to save array list into prefrence file
    public static void saveData(ArrayList loginData, Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(loginData);
        editor.putString("loginData", json);
        editor.apply();
        editor.commit();
    }

    //   logout or delete all prefrence file
    public static void logout(Context context) {
        App.getGlobalPrefs().edit().clear().commit();
        instance = null;
//        context.startActivity(new Intent(context, Login.class));
//        ((Activity) context).finishAffinity();


    }

    //  save data into prefrence file with single key values
    public void saveSingleData(Context context, String Key, String Data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key, Data);
        editor.commit();
    }

    // get data with key values single
    public String getPreferenceData(Context context, String Key) {
        SharedPreferences sharedPreferences = App.getGlobalPrefs();
        return sharedPreferences.getString(Key, null);
    }

    // get data with key values single
    public Boolean getPreferenceData(Context context, String Key, Boolean defaultValue) {
        SharedPreferences sharedPreferences = App.getGlobalPrefs();
        return sharedPreferences.getBoolean(Key, defaultValue);
    }

}