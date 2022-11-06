package com.example.ungdungdatlichcattoc.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.Adapter.ServiceAdapter;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.Service;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_dich_vu);
        gridView =findViewById(R.id.chondichvu_gridview);
        serviceList =new ArrayList<>();
        ServiceAdapter serviceAdapter = new ServiceAdapter(this,R.layout.item_chondichvu,serviceList);
    gridView.setAdapter(serviceAdapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/service/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Service>>callservice = apiService.getServices();
        callservice.enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                if(response.isSuccessful()&&response.body()!=null)
                {
                    serviceList.addAll(response.body());
                    Toast.makeText(getApplicationContext(),"Update Data Successfull",Toast.LENGTH_SHORT).show();
                    gridView.setAdapter(serviceAdapter);
                    serviceAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Lỗi rồi ông cháu: "+t,Toast.LENGTH_SHORT).show();
                Log.e("LoiGETDATA", "onFailure: "+t );
            }
        });
    }
}