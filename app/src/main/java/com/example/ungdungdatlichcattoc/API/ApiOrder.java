package com.example.ungdungdatlichcattoc.API;

import com.example.ungdungdatlichcattoc.model.OrderResponse;
import com.example.ungdungdatlichcattoc.model.RegisterResponse;
import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiOrder {
    @FormUrlEncoded
    @POST("add")
    Call<OrderResponse> order(
            @Field("token") String token,
            @Field("serviceIds") JSONArray serviceIds,
            @Field("stylistId") String stylistId,

            @Field("time") Date time,
            @Field("note") String note,
            @Field("sumPrice") int sumPrice

    );
}
