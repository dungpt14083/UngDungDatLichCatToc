package com.example.ungdungdatlichcattoc.API;

import com.example.ungdungdatlichcattoc.model.HairStylish;
import com.example.ungdungdatlichcattoc.model.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiHairStylish {
    @GET("get-all")
    Call<List<HairStylish>> getHairStylish();


    @FormUrlEncoded
    @POST("stylist-time")
    Call<List<Integer>> getFreeTime(
            @Field("stylistId") String stylistId,
            @Field("date") String date
    );
}