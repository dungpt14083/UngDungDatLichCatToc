package com.example.ungdungdatlichcattoc.model;

public class History {
    public  String _id, status , time , totalTimeString, note , sumPrice , customerId , nameStylist , description , nameUser ;
    public  String [] nameServices ;
    public  String [] serviceIds ;

    public History(String _id, String status, String time, String totalTimeString, String note, String sumPrice, String customerId, String nameStylist, String description, String nameUser, String[] nameServices, String[] serviceIds) {
        this._id = _id;
        this.status = status;
        this.time = time;
        this.totalTimeString = totalTimeString;
        this.note = note;
        this.sumPrice = sumPrice;
        this.customerId = customerId;
        this.nameStylist = nameStylist;
        this.description = description;
        this.nameUser = nameUser;
        this.nameServices = nameServices;
        this.serviceIds = serviceIds;
    }
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalTimeString() {
        return totalTimeString;
    }

    public void setTotalTimeString(String totalTimeString) {
        this.totalTimeString = totalTimeString;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getNameStylist() {
        return nameStylist;
    }

    public void setNameStylist(String nameStylist) {
        this.nameStylist = nameStylist;
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

    public String[] getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(String[] serviceIds) {
        this.serviceIds = serviceIds;
    }
}
