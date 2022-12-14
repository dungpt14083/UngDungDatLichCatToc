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
import com.example.ungdungdatlichcattoc.Adapter.Adapter_TopService;
import com.example.ungdungdatlichcattoc.Interface.InterfaceClickSelectTopService;
import com.example.ungdungdatlichcattoc.MainActivity;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.Service;
import com.example.ungdungdatlichcattoc.model.TopService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopServiceActivity extends AppCompatActivity {
    GridView gridViewTop;
    List<TopService> topServices;
    ImageView btnhomeback;
    ArrayList<String> listnamesevice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_service);
        gridViewTop =findViewById(R.id.TopService_gridview);
        btnhomeback=findViewById(R.id.btnhomeTopServicec);
        topServices = new ArrayList<>();
         listnamesevice = new ArrayList<>();
        Adapter_TopService adapter_topService = new Adapter_TopService(getApplicationContext(), R.layout.item_topservice, topServices, new InterfaceClickSelectTopService() {
            @Override
            public void onClickItemTopservice(TopService topService) {
                try {
                    String[] idsv =new String[]{topService.get_id()};
                    listnamesevice.add(topService.getName());
                    Intent intent = new Intent(getApplicationContext(), DatlichActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("sumprice", topService.getPrice());
                    bundle.putStringArray("listidservice", idsv);
                    bundle.putStringArrayList("listnameservice", listnamesevice);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    finish();
                }catch (Exception e)
                {

                }

            }
        });
        gridViewTop.setAdapter(adapter_topService);
        getTopStylish();
        btnhomeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopServiceActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    void getTopStylish(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/order/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<TopService>> callservice = apiService.getTopServices();
        callservice.enqueue(new Callback<List<TopService>>() {
            @Override
            public void onResponse(Call<List<TopService>> call, Response<List<TopService>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    topServices.addAll(response.body());
                    // Toast.makeText(getApplicationContext(), "Update Data Successfull", Toast.LENGTH_SHORT).show();

                    Adapter_TopService adapter_topService = new Adapter_TopService(getApplicationContext(), R.layout.item_topservice, topServices, new InterfaceClickSelectTopService() {
                        @Override
                        public void onClickItemTopservice(TopService topService) {

                            try {
                                String[] idsv =new String[]{topService.get_id()};
                                listnamesevice.add(topService.getName());
                                Intent intent = new Intent(getApplicationContext(), DatlichActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("sumprice", topService.getPrice());
                                bundle.putStringArray("listidservice", idsv);
                                bundle.putStringArrayList("listnameservice", listnamesevice);
                                intent.putExtras(bundle);
                                startActivity(intent);

                                finish();
                            }catch (Exception e)
                            {

                            }
                        }
                    });
                    gridViewTop.setAdapter(adapter_topService);

                }
            }
            @Override
            public void onFailure(Call<List<TopService>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lỗi rồi ông cháu: " + t, Toast.LENGTH_SHORT).show();
                Log.e("LoiGETDATA", "onFailure: " + t);
            }
        });
    }
}