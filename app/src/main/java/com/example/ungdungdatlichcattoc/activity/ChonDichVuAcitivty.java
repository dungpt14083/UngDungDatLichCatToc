package com.example.ungdungdatlichcattoc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.Adapter.ServiceAdapter;
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

public class ChonDichVuAcitivty extends AppCompatActivity {
    private GridView gridView;
    private List<Service> serviceList;
    public ArrayList<String> listidService;
    public  ArrayList<Integer> listpriceservice;
    public int sumprice;
    MaterialButton btnselect;
    ServiceAdapter serviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_dich_vu);
        gridView = findViewById(R.id.chondichvu_gridview);
        btnselect = findViewById(R.id.chondichvu_btn_chondichvu);
        listidService = new ArrayList<>();
        serviceList = new ArrayList<>();
        serviceAdapter = new ServiceAdapter(this, R.layout.item_chondichvu, serviceList);
        gridView.setAdapter(serviceAdapter);
        getAPIService();
        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   btnselect.setText("v");

                sumprice =0;
                listidService =serviceAdapter.idservice;
                listpriceservice=serviceAdapter.priceservice;
                for(int element : listpriceservice){
                    sumprice+=element;
                }

              //  String[] idsv = (String[]) listidService.toArray();
                String[] idsv = new String[listidService.size()];
                idsv = listidService.toArray(idsv);
                Intent intent = new Intent(getApplicationContext(), DatlichActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("sumprice",sumprice);
                bundle.putStringArray("listidservice",idsv);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    void getAPIService() {
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
                    gridView.setAdapter(serviceAdapter);
                    serviceAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                Log.e("LoiGETDATA", "onFailure: " + t);
            }
        });

    }
}