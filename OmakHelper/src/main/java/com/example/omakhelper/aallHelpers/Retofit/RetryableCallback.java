package com.example.omakhelper.aallHelpers.Retofit;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RetryableCallback<T> implements Callback<T> {

    private static final String TAG = RetryableCallback.class.getSimpleName();
    private final Call<T> call;
    private Context context;

    public RetryableCallback(Call<T> call, Context context) {
        this.call = call;
        this.context = context;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        onFinalResponse(call, response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        // HelperFunctions.theLogger("RetryableCallback", t.getMessage());
        if (t.getMessage().toLowerCase().contains("server")) { // if error contains some keyword, retry the request as well. This is just an example to show you can call retry from either success or failure.
            retryCall();
        } else {
            onFinalFailure(call, t); // if not, finish the call as a failure
        }
    }

    public void onFinalResponse(Call<T> call, Response<T> response) {

    }

    public void onFinalFailure(Call<T> call, Throwable t) { // to be overriden by calling class
        if (t instanceof IOException) {
            Toast.makeText(context, "Input Output Exception", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "API Failure Error :(", Toast.LENGTH_SHORT).show();
        }
    }

    private void retryCall() {
        call.clone().enqueue(this); // clone the original call and enqueue it for retry
    }

}