package com.example.omakhelper.aallHelpers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.omak.readmin.R;
import com.omak.readmin.aallActivities.ProfileActivities.Login;
import com.omak.readmin.aallActivities.ProfileActivities.MyProfile;
import com.omak.readmin.aallActivities.UserSingleProject;
import com.omak.readmin.aallActivities.UserViewComments;
import com.omak.readmin.aallModelsRealm.RealmNotificationModel;
import com.omak.readmin.aallModelsRealm.RealmSingleProjectModel;
import com.omak.readmin.adminPanel.AdminPanelLeads;
import com.omak.readmin.adminPanel.AdminPanelProjects;
import com.omak.readmin.adminPanel.AdminPanelSubscribers;
import com.omak.readmin.adminPanel.AdminPanelViewComments;
import com.omak.readmin.adminPanel.models.RealmModelCalculationsNew;
import com.omak.readmin.adminPanel.models.RealmModelLeads;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

import io.realm.Realm;
import io.realm.RealmResults;

public class NotificationHandler extends FirebaseMessagingService {
    public static Bitmap largImage, bigImage, smallIconImage;
    public static String btnTitle = "";
    public static String type = "";
    public static String postId = "";
    public static String project_id = "";
    public static String EditEmail = "";
    public static String EditPhone = "";
    public static String EditFirstName = "";
    public static String EditLastName = "";
    public static String EditAddress = "";
    public static String lead_id = "";
    public static String last_mod_str_date = "";
    public static String last_calculated = "";
    RealmHelpers realmHelpers;
    Realm realm;
    int nextNotificatoinId;
    HashMap<String, String> notiData;
    String longImageUrl = "", smallImageUrl = "", smallIcon = "", notiTitle = "", notiMessage = "";
    PreferenceFile preferenceFile;
    private Context context;
    private NotificationChannelHelpers mNotificationUtils;
    private RealmResults<RealmModelCalculationsNew> allCalculationOfThisLead;

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        preferenceFile = new PreferenceFile();
        type = getDataKey(remoteMessage, "type");
        notiTitle = getDataKey(remoteMessage, "title");
        notiMessage = getDataKey(remoteMessage, "message");
        btnTitle = getDataKey(remoteMessage, "btn_title");
        longImageUrl = getDataKey(remoteMessage, "img_big");
        smallImageUrl = getDataKey(remoteMessage, "img_icon");
        smallIcon = getDataKey(remoteMessage, "small_icon");
        project_id = getDataKey(remoteMessage, "project_id");
        lead_id = getDataKey(remoteMessage, "lead_id");
        postId = getDataKey(remoteMessage, "post_id");
        EditPhone = getDataKey(remoteMessage, "userphone");
        EditEmail = getDataKey(remoteMessage, "useremail");
        EditFirstName = getDataKey(remoteMessage, "first_name");
        EditLastName = getDataKey(remoteMessage, "last_name");
        EditAddress = getDataKey(remoteMessage, "address");
        last_calculated = getDataKey(remoteMessage, "last_calculated");
        last_mod_str_date = getDataKey(remoteMessage, "last_mod_str_date");

        // store notification into realm
        notiData = new HashMap<>();
        notiData.put("data", new Gson().toJson(remoteMessage.getData()));
        notiData.put("type", getDataKey(remoteMessage, "type"));
        notiData.put("title", getDataKey(remoteMessage, "title"));
        notiData.put("message", getDataKey(remoteMessage, "message"));
        notiData.put("btn_title", getDataKey(remoteMessage, "btn_title"));
        notiData.put("project_id", getDataKey(remoteMessage, "project_id"));
        notiData.put("longImageUrl", getDataKey(remoteMessage, "img_big"));
        notiData.put("smallImageUrl", getDataKey(remoteMessage, "img_icon"));
        notiData.put("smallIcon", getDataKey(remoteMessage, "small_icon"));
        notiData.put("show_notification", "");
        notiData.put("channel_id", "channel_id_general");

        if (!getDataKey(remoteMessage, "remoteShowNotification").equals("")) {
            notiData.put("show_notification", getDataKey(remoteMessage, "remoteShowNotification"));
        }
        if (!getDataKey(remoteMessage, "channel_id").equals("")) {
            notiData.put("channel_id", getDataKey(remoteMessage, "channel_id"));
        }


        showGeneralNotification(remoteMessage);
    }

    private String getDataKey(RemoteMessage remoteMessage, String key) {
        return (remoteMessage.getData().get(key) != null) ? remoteMessage.getData().get(key) : "";
    }

    private void showGeneralNotification(RemoteMessage remoteMessage) {
        realmHelpers = RealmHelpers.getInstance(this);
        realm = com.omak.readmin.aallHelpers.HelperFunctions.getRealm("messages", this);
        RealmNotificationModel.createAndInsert(this, notiData);
        realm.executeTransaction(
                new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Number currentIdNum = realm.where(RealmNotificationModel.class).max("id");
                        nextNotificatoinId = (currentIdNum == null) ? 1 : currentIdNum.intValue();
                    }
                });

        Intent intent = new Intent();
        AtomicReference<Boolean> isRealmUpdated = new AtomicReference<>(false);
        try {
            URL largeImage = new URL(longImageUrl);
            URL bigImages = new URL(smallImageUrl);
            URL smallUrlIcon = new URL(smallIcon);
            largImage = BitmapFactory.decodeStream(largeImage.openConnection().getInputStream());
            bigImage = BitmapFactory.decodeStream(bigImages.openConnection().getInputStream());
            smallIconImage = BitmapFactory.decodeStream(smallUrlIcon.openConnection().getInputStream());
        } catch (IOException e) {
            System.out.println(e);
        }

        String channelId = getString(R.string.normal_notifications);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationCompat =
                new NotificationCompat.Builder(this, channelId)
                        .setLargeIcon(largImage)
                        .setContentText(notiMessage)
                        .setSmallIcon(R.drawable.research_experts_new_icon)
                        .setColor(Color.GREEN)
                        .setContentTitle(getString(R.string.noti_suffix) + notiTitle)
                        .setSound(defaultSoundUri)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(notiMessage))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationCompat.setSmallIcon(R.drawable.rearch_experts_icons);
            notificationCompat.setColor(getResources().getColor(R.color.white));
        } else {
            notificationCompat.setSmallIcon(R.drawable.rearch_experts_icons);
        }
        switch (type) {
            case "new-project":
            case "project-update":
                if (preferenceFile.getPreferenceData(context, WebUrls.USER_ROLE).equals("administrator")) {
                    new RealmHelpers(context).setBooleanFlag(RealmHelpers.allAdminProjectList, false);

                    intent = new Intent(this, AdminPanelProjects.class);
                } else {
                    intent = new Intent(this, UserSingleProject.class);
                }
                intent.putExtra("post_id", postId);
                intent.putExtra("project_id", project_id);
                break;
            case "new-signup":
                new RealmHelpers(context).setBooleanFlag(RealmHelpers.allSubscriberListUpdated, false);
                intent = new Intent(this, AdminPanelSubscribers.class);
                intent.putExtra("userId", getDataKey(remoteMessage, "user_id"));
                break;
            case "user-update":
                intent = new Intent(this, MyProfile.class);
                if (!EditAddress.isEmpty() && !EditFirstName.isEmpty() && !EditLastName.isEmpty() && !EditPhone.isEmpty() && !EditEmail.isEmpty()) {
                    preferenceFile.saveSingleData(this, WebUrls.USER_FIRST_NAME, EditFirstName);
                    preferenceFile.saveSingleData(this, WebUrls.USER_LAST_NAME, EditLastName);
                    preferenceFile.saveSingleData(this, WebUrls.USER_COUNTRY_NAME, EditAddress);
                    preferenceFile.saveSingleData(this, WebUrls.USER_MOBILE, EditPhone);
                    preferenceFile.saveSingleData(this, WebUrls.USER_EMAIL, EditEmail);
                }
                break;
            case "comments-update":
                if (preferenceFile.getPreferenceData(context, WebUrls.USER_ROLE).equals("administrator")) {
                    new RealmHelpers(context).setBooleanFlag(RealmHelpers.getAllCommentsAdminSide, false);
                    intent = new Intent(this, AdminPanelViewComments.class);
                } else {
                    intent = new Intent(this, UserViewComments.class);
                }
                intent.putExtra("post_id", postId);
                intent.putExtra("project_id", project_id);
                break;
//			case "new-calculation":
            case "update-lead":
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmModelLeads realmModelLeads = new RealmModelLeads();
                        Integer leadId = Integer.parseInt(lead_id);
                        realmModelLeads = realmHelpers.getDataFromRealm("id", leadId, RealmModelLeads.class);
                        realmModelLeads.setLast_calculated(last_calculated);
                        realmModelLeads.setLastModStrDate(Integer.parseInt(last_mod_str_date));
                        realm.insert(realmModelLeads);
                    }
                });
                break;
            case "new-lead":
                new RealmHelpers(context).setBooleanFlag(RealmHelpers.allLeadsUpdated, false);
                realmHelpers.setBooleanFlag(RealmHelpers.allCalculationsNewUpdated, false);
                intent = new Intent(this, AdminPanelLeads.class);
                break;
            case "delete-project":
                realmHelpers.deleteFromRealm("projectId", project_id, RealmSingleProjectModel.class);
                intent = new Intent(this, AdminPanelProjects.class);
                break;
            case "delete-lead":
                realmHelpers.deleteFromRealm("id", Integer.parseInt(lead_id), RealmModelLeads.class);
                // get all new calculation against this lead
                allCalculationOfThisLead = realmHelpers.getFilteredDataFromRealm("lead_id", lead_id, RealmModelCalculationsNew.class);
                // for loop to delete a single single new calculation into realm database
                for (int i = 0; i < allCalculationOfThisLead.size(); i++) {
                    final int rowId = (allCalculationOfThisLead.get(i).getId());
                    new RealmHelpers(context).deleteFromRealm1("id", rowId, RealmModelLeads.class);

                    realmHelpers.deleteFromRealm("id", allCalculationOfThisLead.get(i).getId(), RealmModelCalculationsNew.class);
                }
                intent = new Intent(this, AdminPanelLeads.class);
                break;

            case "logout":
            case "password-change":
                realm = com.omak.readmin.aallHelpers.HelperFunctions.getRealm("messages", this);
                // Delete Realm file Heres
                try {
                    realm.beginTransaction();
                    realm.deleteAll();
                    realm.commitTransaction();
                    // Realm file has been deleted.
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // No Realm file to remove.
                }
                App.getGlobalPrefs().edit().clear().commit();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                editor.commit();
                Intent intent1 = new Intent(this, Login.class);
                intent1.addFlags((Intent.FLAG_ACTIVITY_CLEAR_TOP));
                intent1.addFlags((Intent.FLAG_ACTIVITY_NEW_TASK));
                startActivity(intent1);
                break;
            case "Delete_User":
                break;
            case "Delete_Project":
                break;
            case "Delete_Comment":
                break;
        }

        String remoteShowNotification = getDataKey(remoteMessage, "show_notification");

        AtomicReference<Boolean> showNotification = new AtomicReference<Boolean>(true);

        if (!remoteShowNotification.equals("") && remoteShowNotification.equals("false")) {
            showNotification.set(false);
        }

        if (isRealmUpdated.get()) {
        }

        if (intent != null) {
            intent.putExtra("firebase", true);
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notificationCompat
                    .setContentIntent(pendingIntent)
                    .addAction(R.mipmap.re_app_icon, "" + btnTitle, pendingIntent);
        }

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotificationUtils = new NotificationChannelHelpers(getApplicationContext());
            notificationCompat.setChannelId("channel_id_general");
            if (!getDataKey(remoteMessage, "channel_id").equals("")) {
                notificationCompat.setChannelId(getDataKey(remoteMessage, "channel_id"));
            }
            mNotificationUtils.getManager().notify(nextNotificatoinId, notificationCompat.build());
        } else {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(nextNotificatoinId, notificationCompat.build());
        }
    }
}
