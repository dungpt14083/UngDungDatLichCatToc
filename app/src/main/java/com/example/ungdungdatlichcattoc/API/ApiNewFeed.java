package com.example.ungdungdatlichcattoc.Api;

import com.example.ungdungdatlichcattoc.Model.Newfeed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiNewFeed {
    String BASE_URL = "get-all";
    @GET(BASE_URL)
    Call<List<Newfeed>> getNewfeed();
}
