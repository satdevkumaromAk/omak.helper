package com.example.omakhelper.aallHelpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionsHelper {
    public static final int REQUEST_PERMISSION_MULTIPLE = 0;
    public static final int REQUEST_PERMISSION_CAMERA = 1;
    public static final int REQUEST_PERMISSION_LOCATION = 2;
    public static final int REQUEST_READ_CALLLOG = 3;
    private String[] permissions = {
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.SYSTEM_ALERT_WINDOW,
            LocationManager.NETWORK_PROVIDER,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkAndRequestPermissions(Activity activity) {

        int permissionPhone = ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE);
        int permissionLocation = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionReadCallLogs = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CALL_LOG);
        int permissionWritesCallLogs = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CONTACTS);
        int permissionReadConatcts = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS);
        int permissionWindowRequest = ContextCompat.checkSelfPermission(activity, Manifest.permission.SYSTEM_ALERT_WINDOW);

        // Permission List
        List<String> listPermissionsNeeded = new ArrayList<>();

        // Call Phone Permission
        if (permissionPhone != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CALL_PHONE)) {
                Toast.makeText(activity, "CAll_PHONE Permission is required for this app to run", Toast.LENGTH_SHORT).show();
            }
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
        }

        // Read/Write Permission
        if (permissionReadCallLogs != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CALL_LOG);
        }
        if (permissionWritesCallLogs != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_CONTACTS);
        }

        // Location Permission
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (permissionReadConatcts != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
        }

        if (permissionWindowRequest != PackageManager.PERMISSION_GRANTED) {
            // listPermissionsNeeded.add(Manifest.permission.SYSTEM_ALERT_WINDOW);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                    activity,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_PERMISSION_MULTIPLE
            );

            return false;
        }

        return true;
    }

    /**
     * Requests the Camera permission. If the permission has been denied
     * previously, a SnackBar will prompt the user to grant the permission,
     * otherwise it is requested directly.
     */
    public static void requestCameraPermission(Activity activity) {
        // Here, thisActivity is the current activity
        // System.out.println("requestCameraPermission() INITIAL");
        // Toast.makeText(this, "requestCameraPermission() INITIAL",
        // Toast.LENGTH_LONG).show();
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
                // Toast.makeText(activity, "Camera permission is
                // needed for this app to run ",
                // Toast.LENGTH_SHORT).show();
                // System.out.println("requestCameraPermission() SHOW INFO");

                // Show an explanation to the user *asynchronously* -- don't
                // block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA},
                        REQUEST_PERMISSION_CAMERA);

            } else {
                // No explanation needed, we can request the permission.
                // System.out.println("requestCameraPermission() ASK
                // PERMISSION");

                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA},
                        REQUEST_PERMISSION_CAMERA);
            }
            // Permission is granted
        } else {
            System.out.println("requestCameraPermission() PERMISSION ALREADY GRANTED");

        }
    }

    public static void requestLocationPermission(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(activity, "LOCATION permission is needed to display location info ", Toast.LENGTH_SHORT)
                        .show();
                // Show an explanation to the user *asynchronously* -- don't
                // block this thread waiting for the user's response! After the
                // user sees the explanation, try again to request the
                // permission.
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_PERMISSION_LOCATION);

                Toast.makeText(activity, "REQUEST LOCATION PERMISSION", Toast.LENGTH_LONG).show();

            } else {
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_PERMISSION_LOCATION);
                Toast.makeText(activity, "REQUEST LOCATION PERMISSION", Toast.LENGTH_LONG).show();
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            // Permission is granted
        } else {

        }
    }

    public static boolean requestReadCallLog(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.READ_CALL_LOG)) {
                Toast.makeText(activity, "Write permission is needed to create Excel file ", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_CALL_LOG},
                        REQUEST_READ_CALLLOG);
                Toast.makeText(activity, "REQUEST LOCATION PERMISSION", Toast.LENGTH_LONG).show();

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_CALL_LOG},
                        REQUEST_READ_CALLLOG);

            }
        }
        return true;
    }

    public String[] getRequiredPermissionsArray() {
        return this.permissions;
    }

    public boolean checkLocationPermssions(Context context) {
        boolean is_permitted = true;

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            is_permitted = false;
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            is_permitted = false;
        }

        return is_permitted;
    }

}