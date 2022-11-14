package com.example.ungdungdatlichcattoc.model;

public class Order {
    private  String customerId ;
    private  String[] serviceIds ;
    private  String stylelistId ;
    private  String Status ;
    private  String time  ,  totalTime , note , sumPrice ;

    public Order(String customerId, String[] serviceIds, String stylelistId, String status, String time, String totalTime, String note, String sumPrice) {
        this.customerId = customerId;
        this.serviceIds = serviceIds;
        this.stylelistId = stylelistId;
        Status = status;
        this.time = time;
        this.totalTime = totalTime;
        this.note = note;
        this.sumPrice = sumPrice;
    }

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

    public String getStylelistId() {
        return stylelistId;
    }

    public void setStylelistId(String stylelistId) {
        this.stylelistId = stylelistId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice;
    }
}
