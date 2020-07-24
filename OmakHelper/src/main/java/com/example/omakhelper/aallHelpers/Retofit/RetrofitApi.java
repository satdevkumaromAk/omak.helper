package com.example.omakhelper.aallHelpers.Retofit;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface RetrofitApi {
    @GET
    Call<ResponseBody> callGet(@Url String url);

    @POST
    Call<ResponseBody> callPost(@Url String url, @Body RequestBody body);


    @Multipart
    @POST
    Call<ResponseBody> callMultipart(@Url String url, @Part("data") RequestBody data, @Part List<MultipartBody.Part> files);

    @Multipart
    @POST
    Call<ResponseBody> callMultipart(@Url String url, @Part MultipartBody.Part file);

    @POST
    Call<ResponseBody> callLogin(@Url String url, @Body RequestBody body);
}
