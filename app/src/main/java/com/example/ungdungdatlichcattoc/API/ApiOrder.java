package com.example.ungdungdatlichcattoc.API;

import com.example.ungdungdatlichcattoc.model.Order;
import com.example.ungdungdatlichcattoc.model.OrderResponse;
import com.example.ungdungdatlichcattoc.model.TopStylish;

import org.json.JSONArray;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    @FormUrlEncoded
    @POST("history")
    Call<List<Order>> getOder(@Field("token") String customerId) ;

    @GET("topStylist")
    Call<List<TopStylish>> getTopHairStylish();
}
