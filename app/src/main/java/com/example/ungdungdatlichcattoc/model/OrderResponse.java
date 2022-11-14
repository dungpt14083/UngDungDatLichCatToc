package com.example.ungdungdatlichcattoc.model;

import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.Date;
import java.util.List;

public class OrderResponse {
    @SerializedName("paymentType")
    @Expose
    private String paymentType;

    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("serviceIds[]")
    @Expose
    private String[] serviceIds;

    @SerializedName("stylistId")
    @Expose
    private String stylistId;

    @SerializedName("time")
    @Expose
    private Date  time;

    @SerializedName("note")
    @Expose
    private String  note;

    @SerializedName("customerId")
    @Expose
    private  String customerId;



}
