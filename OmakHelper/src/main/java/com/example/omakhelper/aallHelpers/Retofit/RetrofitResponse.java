package com.example.omakhelper.aallHelpers.Retofit;

import org.json.JSONException;

public interface RetrofitResponse {

    void onResponse(int RequestCode, String response) throws JSONException;

}
