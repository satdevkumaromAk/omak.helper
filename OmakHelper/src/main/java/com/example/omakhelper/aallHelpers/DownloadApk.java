package com.example.omakhelper.aallHelpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadApk extends Activity {
    private static ProgressDialog bar;
    private static String TAG = "DownloadApk";
    private static Context context;
    private static Activity activity;
    private static String downloadUrl;

    public DownloadApk(Context context) {
        DownloadApk.context = context;
        activity = (Activity) context;
    }

    private static void OpenNewVersion(String location) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(getUriFromFile(location), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        activity.finish();
    }

    private static Uri getUriFromFile(String location) {
        return Build.VERSION.SDK_INT < 24 ? Uri.fromFile(new File(location + "app-debug.apk")) : FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", new File(location + "app-debug.apk"));
    }

    public void startDownloadingApk(String url) {
        downloadUrl = url;
        if (downloadUrl != null) {
            (new DownloadApk.DownloadNewVersion()).execute();
        }

    }

    private static class DownloadNewVersion extends AsyncTask<String, Integer, Boolean> {
        private DownloadNewVersion() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            if (DownloadApk.bar == null) {
                DownloadApk.bar = new ProgressDialog(DownloadApk.context);
                DownloadApk.bar.setCancelable(false);
                DownloadApk.bar.setMessage("Downloading...");
                DownloadApk.bar.setIndeterminate(true);
                DownloadApk.bar.setCanceledOnTouchOutside(false);
                DownloadApk.bar.show();
            }

        }

        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            DownloadApk.bar.setIndeterminate(false);
            DownloadApk.bar.setMax(100);
            DownloadApk.bar.setProgress(progress[0]);
            String msg = "";
            if (progress[0] > 99) {
                msg = "Finishing... ";
            } else {
                msg = "Downloading... " + progress[0] + "%";
            }

            DownloadApk.bar.setMessage(msg);
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (DownloadApk.bar.isShowing() && DownloadApk.bar != null) {
                DownloadApk.bar.dismiss();
                DownloadApk.bar = null;
            }

            if (result) {
                Toast.makeText(DownloadApk.context, "Update Done", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DownloadApk.context, "Error: Try Again", Toast.LENGTH_SHORT).show();
            }

        }

        protected Boolean doInBackground(String... arg0) {
            Boolean flag = false;

            try {
                URL url = new URL(DownloadApk.downloadUrl);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.setDoOutput(true);
                c.connect();
                String PATH = Environment.getExternalStorageDirectory() + "/Download/";
                File file = new File(PATH);
                file.mkdirs();
                File outputFile = new File(file, "app-debug.apk");
                if (outputFile.exists()) {
                    outputFile.delete();
                }

                FileOutputStream fos = new FileOutputStream(outputFile);
                InputStream is = c.getInputStream();
                int total_size = c.getContentLength();
                byte[] buffer = new byte[1024];
                int len1 = 0;
                int per = 0;
                int downloaded = 0;

                int len2;
                while ((len2 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);
                    downloaded += len1;
                    int per1 = downloaded * 100 / total_size;
                    this.publishProgress(per);
                }

                fos.close();
                is.close();
                DownloadApk.OpenNewVersion(PATH);
                flag = true;
            } catch (MalformedURLException var15) {
                Log.e(DownloadApk.TAG, "Update Error: " + var15.getMessage());
                flag = false;
            } catch (IOException var16) {
                var16.printStackTrace();
            }

            return flag;
        }
    }
}