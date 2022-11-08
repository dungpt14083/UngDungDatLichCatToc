package com.example.ungdungdatlichcattoc.API;

import com.example.ungdungdatlichcattoc.model.LoginResponse;
import com.example.ungdungdatlichcattoc.model.RegisterResponse;
import com.example.ungdungdatlichcattoc.model.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("get-all")
    Call<List<Service>> getServices();

    @FormUrlEncoded
    @POST("reg")
    Call<RegisterResponse> register(
            @Field("phone") String phone,
            @Field("password") String password);

}
