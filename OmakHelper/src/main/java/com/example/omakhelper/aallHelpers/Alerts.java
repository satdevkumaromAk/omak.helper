package com.example.omakhelper.aallHelpers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.omakhelper.R;
import com.google.gson.Gson;

import java.util.concurrent.atomic.AtomicReference;

public class Alerts {

    public static void setAnimation(Context context, int animation, View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(context, animation);
        view.startAnimation(loadAnimation);
    }

    public static void commonAlert(final Context context, String title, String message, String type) {
        TextView tvAlertCommonTitle, tvAlertCommonContent, tvAlertCommonSubmit;
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final AlertDialog alertDialog;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        view = layoutInflater.inflate(R.layout.common_alert_layout, null);
        builder.setView(view);
        tvAlertCommonTitle = view.findViewById(R.id.tvAlertCommonTitle);
        tvAlertCommonContent = view.findViewById(R.id.tvAlertCommonContent);
        tvAlertCommonSubmit = view.findViewById(R.id.btnAlertCommonSubmit);

        tvAlertCommonTitle.setText(title);
        tvAlertCommonContent.setText(message);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        if (type.equals("dismiss")) {
            tvAlertCommonSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
        }
    }

    public static void alertDialerOpenInte(final Context context, final String title,
                                           String message, final Intent intent) {
        final TextView[] tvAlertCommonTitle = new TextView[1];
        TextView tvAlertCommonContent;
        TextView tvAlertCommonSubmit;

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final AlertDialog alertDialog;
        final Boolean[] ok = new Boolean[1];

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        view = layoutInflater.inflate(R.layout.common_alert_layout, null);
        builder.setView(view);
        tvAlertCommonTitle[0] = view.findViewById(R.id.tvAlertCommonTitle);
        tvAlertCommonContent = view.findViewById(R.id.tvAlertCommonContent);
        tvAlertCommonSubmit = view.findViewById(R.id.btnAlertCommonSubmit);

        tvAlertCommonTitle[0].setText(title);
        tvAlertCommonContent.setText(message);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        tvAlertCommonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("getAllProjects", true);
                context.startActivity(intent);
                alertDialog.dismiss();
            }
        });

    }

    public static void log(String tag, String log) {
        Log.e(tag, "log: " + log);
    }

    public static void printTask(String tag, AlmightMainJSonModel task) {
        log(tag, "" + new Gson().toJson(task));
    }

    public static void toast(Context context, String messages) {

        Toast.makeText(context, messages, Toast.LENGTH_LONG).show();
    }

    public static void toast(Context context, String messages, int length) {

        Toast.makeText(context, messages, length).show();
    }

    public static void actionLogout(Context applicationContext) {

        //Clear Shared-Prefrence
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        //Added this code
      /*  AtomicReference<Intent> logoutIntent = null;
        logoutIntent.set(new Intent(applicationContext, Login.class));
        logoutIntent.get().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        logoutIntent.get().setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        applicationContext.startActivity(logoutIntent.get());*/
    }

    public static boolean isAdmin(Context context) {
        PreferenceFile preferenceFile = new PreferenceFile();
        String user_role = preferenceFile.getPreferenceData(context, WebUrls.USER_ROLE);
        return (user_role.equals("administrator"));
    }

    public static void accessDeniedAlert(Context context) {
//        Intent intent = new Intent(context, Home.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        alertDialerOpenInte(context, context.getString(R.string.app_name), "Permission denied", intent);
    }

}
