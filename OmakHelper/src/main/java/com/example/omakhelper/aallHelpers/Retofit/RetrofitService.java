package com.example.omakhelper.aallHelpers.Retofit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.omakhelper.R;
import com.example.omakhelper.aallHelpers.AccessHelpers;
import com.example.omakhelper.aallHelpers.Alerts;
import com.example.omakhelper.aallHelpers.App;
import com.example.omakhelper.aallHelpers.AppFlowHelpers;
import com.example.omakhelper.aallHelpers.PreferenceFile;
import com.example.omakhelper.aallHelpers.WebUrls;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONObject;

import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private Context mcontext;
    private String mUrl;
    private int mValue, mRequestCode;
    private HashMap<String, RequestBody> map;
    private RetrofitResponse mResponse;
    private Call<ResponseBody> mCall;
    private MultipartBody.Part mpart;
    private JSONObject mJsonObject;
    //    private ProgressDialog progressDialog;
    private Dialog progressDialog;
    private ArrayList<MultipartBody.Part> mPartList;
    private ArrayList<MultipartBody.Part> partList;
    private KProgressHUD kProgressHUD;
    private List<MultipartBody.Part> parts, multiPartList;
    private AlertDialog alertDialog;

    //For Get Request
    public RetrofitService(Context context, RetrofitResponse mResponse, String mUrl,
                           int mRequestCode, int mValue) {
        this.mcontext = context;
        this.mResponse = mResponse;
        this.mUrl = mUrl;
        this.mRequestCode = mRequestCode;
        this.mValue = mValue;
    }//For Post Request

    public RetrofitService(Context context, RetrofitResponse mResponse, String mUrl, JSONObject postparam, int mRequestCode, int mValue) {
        this.mcontext = context;
        this.mResponse = mResponse;
        this.mUrl = mUrl;
        this.mJsonObject = postparam;
        this.mRequestCode = mRequestCode;
        this.mValue = mValue;
    }

    //For Multipart Request
    public RetrofitService(Context context, RetrofitResponse mResponse, String mUrl, MultipartBody.Part mpart, int mRequestCode, int mValue) {
        this.mcontext = context;
        this.mResponse = mResponse;
        this.mUrl = mUrl;
        this.mpart = mpart;
        //this.map = map;
        this.mRequestCode = mRequestCode;
        this.mValue = mValue;
    }

    //For Multipart Request
    public RetrofitService(Context context, RetrofitResponse mResponse, String mUrl, List<MultipartBody.Part> multiPartList, JSONObject object, int mRequestCode, int mValue) {
        this.mcontext = context;
        this.mResponse = mResponse;
        this.mUrl = mUrl;
        this.multiPartList = multiPartList;
        this.mJsonObject = object;
        this.mRequestCode = mRequestCode;
        this.mValue = mValue;
    }

    //not used
    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
                                                       String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[]
                                                               chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }};
            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.readTimeout(3, TimeUnit.MINUTES);
            builder.connectTimeout(2, TimeUnit.MINUTES);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void alertOnTimeOut(final Call<ResponseBody> call,
                                final Callback<ResponseBody> callback, String message) {
        try {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mcontext);
            alertDialog.setMessage(message);
            alertDialog.setPositiveButton(mcontext.getResources().getString(R.string.retry), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.setNegativeButton(mcontext.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = alertDialog.create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            try {
                dialog.show();
            } catch (Exception ex) {
            }
        } catch (Exception ex) {
        }
    }

    public void createProgressbar(Boolean b) {
        if (b) {
            kProgressHUD = KProgressHUD.create(mcontext)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(false)
                    .setAnimationSpeed(1)
                    .setDimAmount(0.2f)
                    .show();
        }
    }

    public void dismissProgressBar(Boolean b) {
        if (b) {
            if (kProgressHUD != null) {
                if (kProgressHUD.isShowing()) {
                    kProgressHUD.dismiss();
                }
            }
        }

    }

    //For multipart multiple files
    public void callService(final boolean ProgressDialog) {
        if (!App.getInstance(mcontext).isOnline()) {
            //	Toast.makeText(mcontext, "Internet Connection is required to use the app.", Toast.LENGTH_LONG).show();
            Alerts.toast(mcontext, "Internet Connection is required to use the app.");

            return;
        }
        PreferenceFile preferenceFile = new PreferenceFile();

        try {
            createProgressbar(ProgressDialog);
            OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(2, TimeUnit.MINUTES).connectTimeout(2, TimeUnit.MINUTES).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.researchexperts.in/wp-json/rel/v1/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);

            if (mValue == 1) {
                mCall = retrofitApi.callGet(mUrl);
            } else if (mValue == 2) {
                if (preferenceFile.getPreferenceData(mcontext, WebUrls.LOGIN_OR_NOT) != null) {
                    if (preferenceFile.getPreferenceData(mcontext, WebUrls.LOGIN_OR_NOT).equals("Yes")) {
                        mJsonObject.put("app_version", preferenceFile.getPreferenceData(mcontext, WebUrls.APP_VERSION));
                        mJsonObject.put("client_id", preferenceFile.getPreferenceData(mcontext, WebUrls.USER_ID));
                        mJsonObject.put("client_role", preferenceFile.getPreferenceData(mcontext, WebUrls.USER_ROLE));
                        mJsonObject.put("udid", preferenceFile.getPreferenceData(mcontext, WebUrls.ANDROID_ID));
                        mJsonObject.put("apptoken", preferenceFile.getPreferenceData(mcontext, WebUrls.APPTOKEN));
                        Alerts.log("Task", new Gson().toJson(mJsonObject));

                    }
                }
                mCall = retrofitApi.callPost(mUrl, RequestBody.create(MediaType.parse("application/json"), mJsonObject.toString()));
            } else if (mValue == 3) {
                RequestBody mJsonObjectBody = RequestBody.create(MediaType.parse("multipart/form-data"), mJsonObject.toString());
                mCall = retrofitApi.callMultipart(mUrl, mJsonObjectBody, multiPartList);

            } else if (mValue == 4) {
                //mCall = retrofitApi.callMultipart(mUrl, parts);
                if (mCall == null) {

                }
            }
            mCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String webRes = "";
                    if (!response.isSuccessful()) {
                        if (response.code() == 401) {
                            //     Toast.makeText(mcontext, "Authentication Failed: Please log-in again.", Toast.LENGTH_LONG).show();
                            Alerts.toast(mcontext, "Authentication Failed: Please log-in again.");
                            AccessHelpers.actionLogout(mcontext);
                        } else if (response.code() == 500) {
                            // Toast.makeText(mcontext, "Server side error. Please try again.", Toast.LENGTH_LONG).show();
                            Alerts.toast(mcontext, "Server side error. Please try again.");
                            Activity activity = AppFlowHelpers.getActivity(mcontext);
                            activity.onBackPressed();
                        } else {
                            //Toast.makeText(mcontext, "Error Response Code: " + response.code() + " received. Please try again.", Toast.LENGTH_LONG).show();
                            Alerts.toast(mcontext, "Error Response Code:");
                            //  retryCall to be setup for 3 times only and only when 500 error from server
                            //retryCall(call);
                        }
                    } else {
                        try {
                            String res = response.body().string();
                            if (res != null) {

                            }

                            JSONObject object = new JSONObject(res);
                            String resp = object.getString("success");
                            String msg = object.getString("message");

                            if (msg.equals("An update is required for the app.")) {
                                Alerts.toast(mcontext, "App Update Required. Please go to Playstore and update the app version.");
                                //Toast.makeText(mcontext, "App Update Required. Please go to Playstore and update the app version.", Toast.LENGTH_LONG).show();
                                AlertDialog dialog = new AlertDialog.Builder(mcontext)
                                        .setTitle("New version available")
                                        .setMessage("Please, update app to new version to continue usage.")
                                        .setPositiveButton("Update",
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(WebUrls.APPSTORE_URL));
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        mcontext.startActivity(intent);
                                                    }
                                                }).create();
                                dialog.show();
                            } else {
                                mResponse.onResponse(mRequestCode, res);
                            }

                        } catch (Exception e) {
                            Alerts.commonAlert(mcontext, mcontext.getString(R.string.app_name), "Exception ", "dismiss");
                            e.printStackTrace();
                        }
                    }
                    dismissProgressBar(ProgressDialog);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
                    dismissProgressBar(ProgressDialog);

                    Alerts.commonAlert(mcontext, mcontext.getString(R.string.app_name), mcontext.getString(R.string.network_connection_timeout), "dismiss");
//                    alertOnTimeOut(call, this, mcontext.getString(R.string.connection_time_out));
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    public void customAlertServerError(String title, String message) {
        TextView tvAlertMessageTitle, tvAlertsMessageBody;
        Button btAlertsDecline, btAlertsJoin;
        View view;
        AutoCompleteTextView autoCompleteTextViewMails;
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        final AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
        view = layoutInflater.inflate(R.layout.server_error_layout, null);
        builder.setView(view);
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
        btAlertsJoin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
        btAlertsDecline.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
    }


}
