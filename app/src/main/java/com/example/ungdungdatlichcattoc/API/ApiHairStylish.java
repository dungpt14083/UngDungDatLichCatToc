package com.example.ungdungdatlichcattoc.API;

import com.example.ungdungdatlichcattoc.model.HairStylish;
import com.example.ungdungdatlichcattoc.model.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiHairStylish {
    @GET("get-all")
    Call<List<HairStylish>> getHairStylish();
}
