package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.API.ApiHairStylish;
import com.example.ungdungdatlichcattoc.Adapter.StylishAdapter;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.HairStylish;
import com.example.ungdungdatlichcattoc.model.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HairActivity extends AppCompatActivity {
     RecyclerView gridView;
     List<HairStylish> hairStylishList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.ungdungdatlichcattoc.R.layout.activity_hair);
        gridView = findViewById(R.id.hairstylist_RCV);
        hairStylishList = new ArrayList<>();
        getHairStylishAPI();
        StylishAdapter stylishAdapter = new StylishAdapter(getApplicationContext(), hairStylishList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridView.setLayoutManager(layoutManager);
        gridView.setAdapter(stylishAdapter);
    }

    void getHairStylishAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/hairstylist/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiHairStylish apiHairStylish = retrofit.create(ApiHairStylish.class);
        Call<List<HairStylish>> callhairstylish = apiHairStylish.getHairStylish();
        callhairstylish.enqueue(new Callback<List<HairStylish>>() {
            @Override
            public void onResponse(Call<List<HairStylish>> call, Response<List<HairStylish>> response) {
                hairStylishList.addAll(response.body());
                Toast.makeText(getApplicationContext(), "Update Data Successfull" + hairStylishList.size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<HairStylish>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "lỗi rồi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}