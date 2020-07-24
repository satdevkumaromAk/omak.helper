package com.example.omakhelper.aallHelpers.Retofit;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.omakhelper.aallHelpers.AccessHelpers;
import com.example.omakhelper.aallHelpers.Alerts;
import com.example.omakhelper.aallHelpers.App;
import com.example.omakhelper.aallHelpers.AppFlowHelpers;
import com.example.omakhelper.aallHelpers.AppStatus;
import com.example.omakhelper.aallHelpers.HelperFunctions;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiHelper {

    public static <T> void enqueueWithRetry(final Context context, Call<T> call, final Callback<T> callback) {
        Boolean isOnline = AppStatus.getInstance(context).isOnline();

        if (!isOnline) {
            Alerts.toast(context, "Check your internet connection.");

            Toast.makeText(context, "Check your internet connection.", Toast.LENGTH_LONG).show();
//			Intent intent = new Intent(context, AppFlowNoInternet.class);
//			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			context.startActivity(intent);
        } else {
           /* final ProgressDialog dialog = new ProgressDialog(context);
            dialog.setCancelable(false);
            dialog.setMessage(context.getString(R.string.please_wait_message));
            //dialog.show();*/

            final KProgressHUD kProgressHUD = KProgressHUD.create(context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(false)
                    .setAnimationSpeed(1)
                    .setDimAmount(0.2f)
                    .show();

            call.enqueue(new RetryableCallback<T>(call, context) {
                @Override
                public void onFinalResponse(Call<T> call, Response<T> response) {

                    if (kProgressHUD.isShowing()) {
                        kProgressHUD.dismiss();
                    }
                    if (!response.isSuccessful()) {
                        if (response.code() == 401) {
                            Alerts.toast(context, "Authentication Failed: Please log-in again.");

//							Toast.makeText(context, "Authentication Failed: Please log-in again.", Toast.LENGTH_LONG).show();
                            Realm realm = HelperFunctions.getRealm("messages", context);
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
                            Activity activity = App.getActivity(context);
                            activity.finish();
                        } else if (response.code() == 500) {
                            Alerts.toast(context, "Server side error. Please try again.");
//							Toast.makeText(context, "Server side error. Please try again.", Toast.LENGTH_LONG).show();
                            Activity activity = AppFlowHelpers.getActivity(context);
                            activity.onBackPressed();
                        } else {
                            Alerts.toast(context, "Error Response Code: " + response.code() + " received. Please try again.");
//							Toast.makeText(context, "Error Response Code: " + response.code() + " received. Please try again.", Toast.LENGTH_LONG).show();
                            // ToDo: retryCall to be setup for 3 times only and only when 500 error from server
                            //retryCall(call);
                        }
                    } else {
                        callback.onResponse(call, response);
                    }
                }

                @Override
                public void onFinalFailure(Call<T> call, Throwable t) {
                    if (kProgressHUD.isShowing()) {
                        kProgressHUD.dismiss();
                    }
                    Alerts.toast(context, "FailureResponse: " + new Gson().toJson(t));

                    Alerts.toast(context, "Failure: " + t.getMessage());
//					Toast.makeText(context, "Failure: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    callback.onFailure(call, t);
                }

            });
        }
    }

    public static <T> void enqueueWithRetryWithoutDialog(final Context context, Call<T> call, final Callback<T> callback) {
        Boolean isOnline = AppStatus.getInstance(context).isOnline();

        if (!isOnline) {
            Alerts.toast(context, "Check your internet connection.");
//			Toast.makeText(context, "Check your internet connection.", Toast.LENGTH_LONG).show();
            //Intent intent = new Intent(context, AppFlowNoInternet.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //context.startActivity(intent);
        } else {

            call.enqueue(new RetryableCallback<T>(call, context) {
                @Override
                public void onFinalResponse(Call<T> call, Response<T> response) {
                    if (!response.isSuccessful()) {
                        if (response.code() == 401) {
                            Alerts.toast(context, "Authentication Failed: Please log-in again.");
//							Toast.makeText(context, "Authentication Failed: Please log-in again.", Toast.LENGTH_LONG).show();
                            AccessHelpers.actionLogout(context);
                            Activity activity = App.getActivity(context);
                            activity.finish();
                        } else {
                            Alerts.toast(context, "Error Response Code: " + response.code() + " received. Please try again.");
//							Toast.makeText(context, "Error Response Code: " + response.code() + " received. Please try again.", Toast.LENGTH_LONG).show();
                            // ToDo: retryCall to be setup for 3 times only and only when 500 error from server
                            // retryCall();
                        }
                    } else {
                        callback.onResponse(call, response);
                    }
                }

                @Override
                public void onFinalFailure(Call<T> call, Throwable t) {
                    callback.onFailure(call, t);
                }
            });
        }
    }

    public static boolean isCallSuccess(Response response) {
        int code = response.code();
        return (code >= 200 && code < 300);
    }


}