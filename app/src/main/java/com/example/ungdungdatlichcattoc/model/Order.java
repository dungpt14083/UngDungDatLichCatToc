package com.example.ungdungdatlichcattoc.model;

public class Order {
    private  String customerId ;
    private  String[] serviceIds ;
    private  String stylelistId ;
    private  String Status ;
    private String nameStylist ;
    private  String time  , totalTime , note , sumPrice  , description , nameUser ;
    private  String [] nameServices ;

    public Order(String customerId, String[] serviceIds, String stylelistId, String status, String nameStylist, String time, String totalTime, String note, String sumPrice, String description, String nameUser, String[] nameServices) {
        this.customerId = customerId;
        this.serviceIds = serviceIds;
        this.stylelistId = stylelistId;
        Status = status;
        this.nameStylist = nameStylist;
        this.time = time;
        this.totalTime = totalTime;
        this.note = note;
        this.sumPrice = sumPrice;
        this.description = description;
        this.nameUser = nameUser;
        this.nameServices = nameServices;
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

    public String getNameStylist() {
        return nameStylist;
    }

    public void setNameStylist(String nameStylist) {
        this.nameStylist = nameStylist;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String[] getNameServices() {
        return nameServices;
    }

    public void setNameServices(String[] nameServices) {
        this.nameServices = nameServices;
    }
}
