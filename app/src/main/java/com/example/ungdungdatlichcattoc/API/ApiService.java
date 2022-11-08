package com.example.ungdungdatlichcattoc.Api;

import com.example.ungdungdatlichcattoc.model.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {
    @GET("get-all")
    Call<List<Service>> getServices();
}
