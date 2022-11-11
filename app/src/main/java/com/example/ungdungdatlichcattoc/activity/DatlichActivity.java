package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.API.ApiHairStylish;
import com.example.ungdungdatlichcattoc.MainActivity;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.HairStylish;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DatlichActivity extends AppCompatActivity  {
    ImageView btnHomeBack;
    private List<HairStylish> hairStylishList;
    LinearLayout layouthairstylish;
    Spinner spinnerStylish;
    public TextView tvNameStylish;
    CardView crv_chonService;
    TextView tvservice;
    String[] listidservice;
    int sumprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datlich);
        btnHomeBack = findViewById(R.id.btnhomeDatLich);
        hairStylishList = new ArrayList<>();
        spinnerStylish = findViewById(R.id.spinerstylish);
        tvNameStylish = findViewById(R.id.tv_datlich_namestylish);
        crv_chonService = findViewById(R.id.datlich_crv_chondichvu);
        tvservice = findViewById(R.id.tv_datlich_service);
        layouthairstylish = findViewById(R.id.layout_selectstylish);
        intentControl();
        getdataService();
        //fixbug
    }



    void getdataService() {
        sumprice = 0;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
            listidservice = bundle.getStringArray("listidservice");
            sumprice = bundle.getInt("sumprice");
            tvservice.setText("Bạn đã chọn: " + listidservice.length + "Dịch Vụ " + "tổng tiền " + String.valueOf(bundle.getInt("sumprice")));
        }
    }

    void intentControl() {
        crv_chonService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChonDichVuAcitivty.class);
                startActivity(intent);
            }
        });
        btnHomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHome);
            }
        });
    }


}