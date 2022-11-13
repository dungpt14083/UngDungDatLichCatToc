package com.example.ungdungdatlichcattoc.API;

import com.example.ungdungdatlichcattoc.model.Newfeed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiNewFeed {
    String BASE_URL = "get-all";
    @GET(BASE_URL)
    Call<List<Newfeed>> getNewfeed();
}
