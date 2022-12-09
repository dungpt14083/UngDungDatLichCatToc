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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String[] getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(String[] serviceIds) {
        this.serviceIds = serviceIds;
    }

    public String getStylistId() {
        return stylistId;
    }

    public void setStylistId(String stylistId) {
        this.stylistId = stylistId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

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
