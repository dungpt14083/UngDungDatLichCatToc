package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.API.ApiHairStylish;
import com.example.ungdungdatlichcattoc.Adapter.HairStylishAdapter;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.HairStylish;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HairStylishActivity extends AppCompatActivity {
    private GridView gridView;
    private List<HairStylish> hairStylishList;
    HairStylishAdapter hairStylishAdapter;
    public String[] listidservice;
    public int sumprice;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair_stylish);
        gridView = findViewById(R.id.grv_hair_stylish);
        hairStylishList = new ArrayList<>();
        hairStylishAdapter = new HairStylishAdapter(this, R.layout.layout_item_stylish_rcv, hairStylishList);
        gridView.setAdapter(hairStylishAdapter);
        getServiceData();
        getHairStylishAPI();



    }

    void getServiceData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
                listidservice = bundle.getStringArray("listidservice");
                sumprice = bundle.getInt("sumprice");
                Toast.makeText(getApplicationContext(),"size list "+listidservice.length,Toast.LENGTH_SHORT).show();
        }
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
                gridView.setAdapter(hairStylishAdapter);
            }

            @Override
            public void onFailure(Call<List<HairStylish>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "lỗi rồi", Toast.LENGTH_SHORT).show();
            }
        });
    }


}