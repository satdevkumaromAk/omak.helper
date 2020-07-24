package com.example.omakhelper.aallHelpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class AccessHelpers {

    public static final String LOGGED_IN_PREF = "logged_in_status";
    public static final String IS_FIRST_LAUNCH = "is_first_launch";
    public static final String IS_INTRO_FIRST_LAUNCH = "is_intro_first_launch";
    public static final String IS_SPOTLIGHT_FIRST_LAUNCH = "is_spotlight_first_launch";
    public static final String IS_SIMCHOCIE_FIRST_LAUNCH = "is_simchoice_first_launch";
    private final List<String> strings = new ArrayList<>();

    public static boolean isAdminUser(Context context) {
        return true;
    }

    public static void returnIfNotAdmin(Context context) {
        if (!isAdminUser(context)) {
            Activity activity = AppFlowHelpers.getActivity(context);
            activity.onBackPressed();
        }
    }

    /// Logout function here
    public static void actionLogout(Context applicationContext) {
        Realm realm = HelperFunctions.getRealm("messages", applicationContext);

        //Delete Realm file Heres
        try {
            realm.beginTransaction();
            realm.deleteAll();
            realm.commitTransaction();
            //Realm file has been deleted.
        } catch (Exception ex) {
            ex.printStackTrace();
            //No Realm file to remove.
        }


        //Clear Shared-Prefrence testing shared prefrence
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putBoolean(IS_FIRST_LAUNCH, false);
        editor.commit();

//        //Added this code
//        Intent logoutIntent = new Intent(applicationContext, Login.class);
//        logoutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        applicationContext.startActivity(logoutIntent);
    }

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the Login Status
     *
     * @param context
     * @param loggedIn
     */
    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }

    /**
     * Get the Login Status
     *
     * @param context
     * @return boolean: login status
     */
    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }
}