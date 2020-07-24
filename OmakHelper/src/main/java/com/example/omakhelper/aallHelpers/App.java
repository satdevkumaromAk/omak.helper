package com.example.omakhelper.aallHelpers;

import android.app.Activity;
import android.app.Application;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class App extends Application{

    public static App mInstance;
    public static Context context;
    private static App instance = new App();
    PreferenceFile preferenceFile;
    ConnectivityManager connectivityManager;
    boolean connected = false;

    public static SharedPreferences getGlobalPrefs() {
        return getContext().getSharedPreferences("RESEARCH_EXPERT", 0);
    }

    public static Context getContext() {
        return context;
    }

    public static App getInstance(Context ctx) {
        context = ctx.getApplicationContext();
        return instance;
    }



    public static boolean contactExists(Context context, String number) {
        /// number is the phone number
        Uri lookupUri = Uri.withAppendedPath(
                ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number));
        String[] mPhoneNumberProjection = {ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.NUMBER,
                ContactsContract.PhoneLookup.DISPLAY_NAME};
        Cursor cur = context.getContentResolver().query(lookupUri, mPhoneNumberProjection,
                null, null, null);
        try {
            if (cur.moveToFirst()) {
                return true;
            }
        } finally {
            if (cur != null)
                cur.close();
        }
        return false;
    }

    public static void openSaveContact(Context context, String name, String number, String email) {
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);

        if (!name.isEmpty()) {
            intent.putExtra(ContactsContract.Intents.Insert.NAME, HelperFunctions.upperCaseLetter(name));
        }

        if (!number.isEmpty()) {
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, number);
            intent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
        }

        if (!email.isEmpty()) {
            intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
            intent.putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
        }

        context.startActivity(intent);
    }

    public static boolean UpdateContact(String name, String number, Context context) {
        boolean success = true;
        try {
            ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

            ops.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                    .withSelection(ContactsContract.CommonDataKinds.Phone._ID + "=? AND " +
                                    ContactsContract.Contacts.Data.MIMETYPE + "='" +
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "'",
                            new String[]{name})
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
                    .build());

            ops.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                    .withSelection(ContactsContract.CommonDataKinds.Phone._ID + "=? AND " +
                                    ContactsContract.Contacts.Data.MIMETYPE + "='" +
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "'",
                            new String[]{name})
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
                    .build());
            try {
                context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
                Toast.makeText(context, "Contact is successfully Edit", Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (OperationApplicationException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public static boolean openWhatsapp(Context context, String number, String name) {
        Boolean success = false;
        try {
            String bodyMessageFormal = "Hello";
            bodyMessageFormal += (name == "") ? "" : " " + HelperFunctions.upperCaseLetter(name);
            bodyMessageFormal += ", \n\n"; // Replace with your message.

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.putExtra(Intent.EXTRA_TEXT, "");
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
            intent.setType("text/rtf");
            intent.setData(Uri.parse("https://wa.me/" + number + "/?text=" + bodyMessageFormal));
            context.startActivity(intent);
            success = true;
        } catch (Exception e) {
            Alerts.toast(context, "Message failed: " + e.getMessage());
            //Toast.makeText(context, "Message failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return success;
    }

    public static boolean openSmsApp(Context context, String number, String name) {
        Boolean success = false;
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", number);
        smsIntent.putExtra("sms_body", "Hi " + name + ",");
        smsIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        if (smsIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(smsIntent);
        }
        success = true;

        return success;
    }

    public static Activity getActivity(Context context) {
        if (context == null) {
            return null;
        } else if (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            } else {
                return getActivity(((ContextWrapper) context).getBaseContext());
            }
        }

        return null;
    }

    public void onCreate() {
        super.onCreate();

        mInstance = this;
        context = getApplicationContext();
        System.gc();
        selectedLang();


    }

    public void selectedLang() {
        preferenceFile = new PreferenceFile();
    }

    //////////////////isOnline method check network/////////////////
    public boolean isOnline() {
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
            return connected;
        } catch (Exception e) {
            System.out.println("CheckConnectivity Exception: " + e.getMessage());
            Log.v("connectivity", e.toString());
        }
        return connected;
    }


}
