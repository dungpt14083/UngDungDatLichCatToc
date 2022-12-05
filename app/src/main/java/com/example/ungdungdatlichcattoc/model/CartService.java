package com.example.ungdungdatlichcattoc.model;

public class CartService {
    private String idservice;
    private String nameservice;
    private Integer priceservice;

    public CartService(String idservice, String nameservice, Integer priceservice) {
        this.idservice = idservice;
        this.nameservice = nameservice;
        this.priceservice = priceservice;
    }

    public CartService() {
    }

    public String getIdservice() {
        return idservice;
    }

    public void setIdservice(String idservice) {
        this.idservice = idservice;
    }

    public String getNameservice() {
        return nameservice;
    }

    public void setNameservice(String nameservice) {
        this.nameservice = nameservice;
    }

    public Integer getPriceservice() {
        return priceservice;
    }

    public void setPriceservice(Integer priceservice) {
        this.priceservice = priceservice;
    }
}
