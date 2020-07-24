package com.example.omakhelper.aallHelpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;

public class IncomingSms extends BroadcastReceiver {
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();

    public void onReceive(Context context, Intent intent) {
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
               /* for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String senderNum = currentMessage.getDisplayOriginatingAddress();
                    String messageBody = currentMessage.getDisplayMessageBody();
                    if(messageBody.contains("Research Experts") && messageBody.contains("OTP")) {
                        String otp = currentMessage.getDisplayMessageBody().split(":")[0];

                        Intent myIntent = new Intent("otp");
                        myIntent.putExtra("message",otp);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(myIntent);
                    }
                    // Show Alert
                }*/ // end for loop
            } // bundle is null
        } catch (Exception e) {
        }
    }
}