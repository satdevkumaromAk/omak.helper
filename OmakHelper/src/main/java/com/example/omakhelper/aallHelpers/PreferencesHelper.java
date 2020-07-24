package com.example.omakhelper.aallHelpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesHelper {
    public static final String enable_caller_popup = "enable_caller_popup";
    public static final String choose_journal = "choose_journal";
    private static final String PREFS_NAME = "preferenceName";

    public static boolean getPreference(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, true);
    }

    public static String getPreference(Context context, String key, String defaultValue) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, defaultValue);
    }

}