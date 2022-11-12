package com.example.ungdungdatlichcattoc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class OrderResponse {
    @SerializedName("customerId")
    @Expose
    private String customerId;

    @SerializedName("serviceIds")
    @Expose
    private String[] serviceIds;

    @SerializedName("stylistId")
    @Expose
    private String stylistId;

    @SerializedName("time")
    @Expose
    private Date time;

    @SerializedName("note")
    @Expose
    private String note;

    @SerializedName("sumPrice")
    @Expose
    private int sumPrice;

    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
