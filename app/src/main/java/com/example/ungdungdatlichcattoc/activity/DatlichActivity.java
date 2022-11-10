package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.API.ApiHairStylish;
import com.example.ungdungdatlichcattoc.Adapter.StylishAdaperSpiner;
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

public class DatlichActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    ImageView btnHomeBack;
    private List<HairStylish> hairStylishList;
    Spinner spinnerStylish;
    TextView tvNameStylish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datlich);
        btnHomeBack = findViewById(R.id.btnhomeDatLich);
        hairStylishList =new ArrayList<>();
        spinnerStylish = findViewById(R.id.spinerstylish);
        tvNameStylish = findViewById(R.id.datlich_tv_namestylish);
        btnHomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHome);
            }
        });
        getHairStylishAPI();
        StylishAdaperSpiner stylishAdaperSpiner = new StylishAdaperSpiner(this,hairStylishList);
        if(stylishAdaperSpiner != null){
            spinnerStylish.setAdapter(stylishAdaperSpiner);
        }
        spinnerStylish.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HairStylish hairStylish = hairStylishList.get(i);
                tvNameStylish.setText(hairStylish.getNameStylist());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tvNameStylish.setText("đéo có gì");
            }
        });


        //fixbug
    }
    void getHairStylishAPI(){
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
                Toast.makeText(getApplicationContext(), "Update Data Successfull", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<HairStylish>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lỗi rồi ông cháu: " + t, Toast.LENGTH_SHORT).show();
                Log.e("LoiGETDATA", "onFailure: " + t);
            }
        });

    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}