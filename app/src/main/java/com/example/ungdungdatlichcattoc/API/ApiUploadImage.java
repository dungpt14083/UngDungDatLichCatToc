package com.example.ungdungdatlichcattoc.API;

import com.example.ungdungdatlichcattoc.model.CusstomerInfo;
import com.example.ungdungdatlichcattoc.model.ResponeAvatandNameCustomer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiUploadImage {
    public static String DOMAIN = "http://io.supermeo.com:8000/customer/";
    Gson gson =new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();
    ApiUploadImage apiUploadImage = new Retrofit.Builder()
            .baseUrl(DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiUploadImage.class);

    @Multipart
    @POST("update")
     Call<CusstomerInfo> uploadavatar(@Part("token") RequestBody token, @Part MultipartBody.Part image) ;


}
