package com.example.ungdungdatlichcattoc.API;

import com.example.ungdungdatlichcattoc.model.CusstomerInfo;
import com.example.ungdungdatlichcattoc.model.LoginResponse;
import com.example.ungdungdatlichcattoc.model.RegisterResponse;
import com.example.ungdungdatlichcattoc.model.Service;
import com.example.ungdungdatlichcattoc.model.TopService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("get-all")
    Call<List<Service>> getServices();

    @GET("topService")
    Call<List<TopService>> getTopServices();

    @GET("get-all")
    Call<List<CusstomerInfo>> getallUser();
    @FormUrlEncoded
    @POST("reg")
    Call<RegisterResponse> register(
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("topicId") String topicId,
            @Field("body") String body,
            @Field("title") String title);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("uniqueId") String uniqueId
    );
}
