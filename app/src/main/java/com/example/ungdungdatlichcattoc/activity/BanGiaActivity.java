package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_BangGia;
import com.example.ungdungdatlichcattoc.MainActivity;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.Service;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BanGiaActivity extends AppCompatActivity {
    private GridView gridView;
    private List<Service> serviceList;
    MaterialButton banggia_btn_datlich;
    ImageView btnhomeback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_gia);
        gridView =findViewById(R.id.banggia_gridview);
        banggia_btn_datlich=findViewById(R.id.banggia_btn_datlich);
        serviceList = new ArrayList<>();
        Adapter_BangGia adapter_bangGia = new Adapter_BangGia(getApplicationContext() , R.layout.item_banggia , serviceList);
        gridView.setAdapter(adapter_bangGia);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/service/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Service>> callservice = apiService.getServices();
        callservice.enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    serviceList.addAll(response.body());
                   // Toast.makeText(getApplicationContext(), "Update Data Successfull", Toast.LENGTH_SHORT).show();
                    gridView.setAdapter(adapter_bangGia);

                }
            }
            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lỗi rồi ông cháu: " + t, Toast.LENGTH_SHORT).show();
                Log.e("LoiGETDATA", "onFailure: " + t);
            }
        });
        btnhomeback = findViewById(R.id.btnhomeBangGia);
        btnhomeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
                Intent intentHOme =new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHOme);
            }
        });
    }
}