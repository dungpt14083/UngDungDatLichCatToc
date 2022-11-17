package com.example.ungdungdatlichcattoc.API;

import com.example.ungdungdatlichcattoc.model.CusstomerInfo;
import com.example.ungdungdatlichcattoc.model.LoginResponse;
import com.example.ungdungdatlichcattoc.model.OrderResponse;

import org.json.JSONArray;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiCustomer {
    @FormUrlEncoded
    @POST("update")
    Call<CusstomerInfo> UpdateCustomer(
            @Field("token") String token,
            @Field("nameUser") String serviceIds,
            @Field("address") String address,
            @Field("birthOfYear") Date birthOfYear
    );
}
