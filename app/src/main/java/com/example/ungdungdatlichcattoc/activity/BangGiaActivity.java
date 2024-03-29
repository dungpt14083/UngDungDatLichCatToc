package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_BangGia;
import com.example.ungdungdatlichcattoc.Interface.InterfaceClickServiceDetail;
import com.example.ungdungdatlichcattoc.MainActivity;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BangGiaActivity extends AppCompatActivity {
    private GridView gridView;
    private List<Service> serviceList;
    Button banggia_btn_datlich;
    ImageView btnhomeback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_gia);
        gridView =findViewById(R.id.banggia_gridview);
        banggia_btn_datlich=findViewById(R.id.banggia_btn_datlich);
        serviceList = new ArrayList<>();
        Adapter_BangGia adapter_bangGia = new Adapter_BangGia(getApplicationContext(), R.layout.item_banggia, serviceList, new InterfaceClickServiceDetail() {
            @Override
            public void clickService(Service service) {

                Intent intent = new Intent(getApplicationContext(), DetailServiceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("imageDetail", service.getImages());
                bundle.putString("idServiceDetail", service.get_id());
                bundle.putInt("priceServiceDetail",service.getPrice());
                bundle.putString("descServiceDetail",service.getDescribe());
                bundle.putString("nameServiceDetail",service.getNameService());

                intent.putExtras(bundle);
                startActivity(intent);

                finish();
            }
        });
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
                    Adapter_BangGia adapter_bangGia = new Adapter_BangGia(getApplicationContext(), R.layout.item_banggia, serviceList, new InterfaceClickServiceDetail() {
                        @Override
                        public void clickService(Service service) {

                            try {
                                Intent intent = new Intent(getApplicationContext(), DetailServiceActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("imageDetail", service.getImages());
                                bundle.putString("idServiceDetail", service.get_id());
                                bundle.putInt("priceServiceDetail",service.getPrice());
                                bundle.putString("descServiceDetail",service.getDescribe());
                                bundle.putString("nameServiceDetail",service.getNameService());

                                intent.putExtras(bundle);
                                startActivity(intent);

                                finish();
                            }catch (Exception e){

                            }

                        }
                    });
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
        banggia_btn_datlich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DatlichActivity.class));
            }
        });
    }
}