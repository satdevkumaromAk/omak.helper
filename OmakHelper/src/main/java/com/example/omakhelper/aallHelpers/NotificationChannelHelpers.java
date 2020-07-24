package com.example.omakhelper.aallHelpers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;


public class NotificationChannelHelpers extends ContextWrapper {
    public static final String CHANNEL_ID_GENERAL = "channel_id_general";
    public static final String CHANNEL_ID_PROJECTS = "channel_id_projects";
    public static final String CHANNEL_ID_COMMENTS = "channel_id_comments";
    public static final String CHANNEL_ID_ACCOUNT = "channel_id_account";
    public static final String CHANNEL_NAME_GENERAL = "General";
    public static final String CHANNEL_NAME_PROJECTS = "Projects";
    public static final String CHANNEL_NAME_COMMENTS = "Comments ";
    public static final String CHANNEL_NAME_ACCOUNT = "Account Related";
    public static String type = "";
    private NotificationManager mManager;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationChannelHelpers(Context context) {
        super(context);
        createChannels();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels() {

        // create android channel
        NotificationChannel generalChannel = new NotificationChannel(CHANNEL_ID_GENERAL,
                CHANNEL_NAME_GENERAL, NotificationManager.IMPORTANCE_DEFAULT);
        // Sets whether notifications posted to this channel should display notification lights
        generalChannel.enableLights(true);
        // Sets whether notification posted to this channel should vibrate.
        generalChannel.enableVibration(true);
        // Sets the notification light color for notifications posted to this channel
        generalChannel.setLightColor(Color.RED);
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        generalChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(generalChannel);

        // create android channel
        NotificationChannel projectChannel = new NotificationChannel(CHANNEL_ID_PROJECTS,
                CHANNEL_NAME_PROJECTS, NotificationManager.IMPORTANCE_DEFAULT);
        // Sets whether notifications posted to this channel should display notification lights
        projectChannel.enableLights(true);
        // Sets whether notification posted to this channel should vibrate.
        projectChannel.enableVibration(true);
        // Sets the notification light color for notifications posted to this channel
        projectChannel.setLightColor(Color.GREEN);
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        projectChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(projectChannel);


        // create android channel
        NotificationChannel accountChannel = new NotificationChannel(CHANNEL_ID_ACCOUNT,
                CHANNEL_NAME_ACCOUNT, NotificationManager.IMPORTANCE_DEFAULT);
        // Sets whether notifications posted to this channel should display notification lights
        accountChannel.enableLights(true);
        // Sets whether notification posted to this channel should vibrate.
        accountChannel.enableVibration(true);
        // Sets the notification light color for notifications posted to this channel
        accountChannel.setLightColor(Color.GREEN);
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        accountChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(accountChannel);


        // create android channel
        NotificationChannel commentsChannel = new NotificationChannel(CHANNEL_ID_COMMENTS,
                CHANNEL_NAME_COMMENTS, NotificationManager.IMPORTANCE_DEFAULT);
        // Sets whether notifications posted to this channel should display notification lights
        commentsChannel.enableLights(true);
        // Sets whether notification posted to this channel should vibrate.
        commentsChannel.enableVibration(true);
        // Sets the notification light color for notifications posted to this channel
        commentsChannel.setLightColor(Color.GREEN);
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        commentsChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(commentsChannel);


    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }
}
