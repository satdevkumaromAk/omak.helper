package com.example.omakhelper.aallHelpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.omakhelper.R;


public class AppFlowHelpers {
    public static Button btAlertsDecline, btAlertsJoin;
    public static AlertDialog alertDialog;

    public static Boolean cancel = false;


    public static AlertDialog commonAlertDialog(final Context context, String title, String message) {
        TextView tvAlertMessageTitle, tvAlertsMessageBody;
        View view;
        ImageView ivAlertBanner;
        LinearLayout llAlertsDialogBox;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final AlertDialog alertDialog;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        view = layoutInflater.inflate(R.layout.common_alert_layout, null);
        builder.setView(view);
        tvAlertMessageTitle = view.findViewById(R.id.tvAlertMessageTitle);
        tvAlertsMessageBody = view.findViewById(R.id.tvAlertsMessageBody);
        btAlertsDecline = view.findViewById(R.id.btAlertsDecline);
        btAlertsJoin = view.findViewById(R.id.btAlertsJoin);
        tvAlertMessageTitle.setText(title);
        tvAlertsMessageBody.setText(message);


        builder.setCancelable(false);
        alertDialog = builder.create();
        return alertDialog;
    }

    public static Boolean commonAlert(final Context context, String title, String message, String type) {
        TextView tvAlertMessageTitle, tvAlertsMessageBody;
        Button btAlertsDecline, btAlertsJoin;
        View view;
        ImageView ivAlertBanner;
        LinearLayout llAlertsDialogBox;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        view = layoutInflater.inflate(R.layout.common_alert_layout, null);
        builder.setView(view);
        llAlertsDialogBox = view.findViewById(R.id.llAlertsDialogBox);
        tvAlertMessageTitle = view.findViewById(R.id.tvAlertMessageTitle);
        tvAlertsMessageBody = view.findViewById(R.id.tvAlertsMessageBody);
        btAlertsDecline = view.findViewById(R.id.btAlertsDecline);
        btAlertsJoin = view.findViewById(R.id.btAlertsJoin);
        tvAlertMessageTitle.setText(title);
        tvAlertsMessageBody.setText(message);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        if (type.equals("dismiss")) {
            btAlertsJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 /* UserLoginActivity activity= new UserLoginActivity();
                   activity.serviceLoginApi();*/
                    alertDialog.dismiss();
                }
            });
            btAlertsDecline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancel = true;
                    alertDialog.dismiss();
                }
            });
        }

        return cancel;
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

    public static void ShowShowCase(final Context context, String title, String text, int viewId, final int type) {

    }

    public void makeNewCall(Context context, String callerId) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + callerId));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void openDialIntent(Context context, String callerId) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + callerId));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /////////Matrail Alert Box////
          /*  new MaterialAlertDialogBuilder(this)
                    .setTitle(getString(R.string.app_name))
                    .setMessage("Do you really want to exit?")
                    .setIcon(R.mipmap.logo)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.finishAffinity(MainActivity.this);
                            finish();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
            */


}

