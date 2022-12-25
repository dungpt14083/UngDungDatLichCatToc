package com.example.ungdungdatlichcattoc.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import com.example.ungdungdatlichcattoc.API.ApiOrder;
import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_lichsu;
import com.example.ungdungdatlichcattoc.Adapter.HairStylishSpinerAdapter;
import com.example.ungdungdatlichcattoc.Interface.InterfaceHistory;
import com.example.ungdungdatlichcattoc.MainActivity;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.CusstomerInfo;
import com.example.ungdungdatlichcattoc.model.LoginResponse;
import com.example.ungdungdatlichcattoc.model.Order;
import com.example.ungdungdatlichcattoc.model.OrderResponse;
import com.example.ungdungdatlichcattoc.model.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LichSuCutActivity extends AppCompatActivity {
    ImageView btnhomebhack;
    SharedPreferences prefs;
    String token;
    List<Order> orderList = new ArrayList<>();
    Adapter_lichsu adapterLichsu;
    ListView listView;
    AppCompatButton btn_lichsucut_order;
    CardView btn_lichsucut_null;
    public ArrayList<String> listnamesevice;
    public ArrayList<String> listnameseviceNew;
    private String[] listIdService;
    private ArrayList<String> listIdServiceNew;
    int sumprice;
    private ArrayList<Service> serviceArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lichsucut);

        listnamesevice = new ArrayList<>();
        serviceArrayList=new ArrayList<>();
        //check dk
        listnameseviceNew= new ArrayList<>();
        listIdServiceNew= new ArrayList<>();
        //end
        btnhomebhack = findViewById(R.id.btnhomeLichSuCut);
        adapterLichsu = new Adapter_lichsu(orderList, getApplicationContext(), new InterfaceHistory() {
            @Override
            public void onClickItemHistory(Order order) {

            }
        });
        sumprice=0;
        listView = findViewById(R.id.lv_lichsucut);
        btn_lichsucut_order = findViewById(R.id.btn_lichsucut_order);
        btn_lichsucut_null = findViewById(R.id.crv_lichsucut_null);
        if (orderList.size() > 0) {
            btn_lichsucut_null.setVisibility(View.GONE);
        }
        btn_lichsucut_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DatlichActivity.class);
                startActivity(intent);
            }
        });
        btnhomebhack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
                Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHome);
            }
        });
        //
        token();
        getAPIService();
        String tokenls = token();
        history(tokenls);
    }

    private void history(String customerId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/order/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiOrder apiOrder = retrofit.create(ApiOrder.class);
        Call<List<Order>> call = apiOrder.getOder(customerId);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {

                orderList.addAll(response.body());

                adapterLichsu = new Adapter_lichsu(orderList, getApplicationContext(), new InterfaceHistory() {
                    @Override
                    public void onClickItemHistory(Order order) {

                        String[] namesv = order.getNameServices();

                        listnamesevice.addAll(Arrays.asList(namesv));
                        //sum price
                        //start check dịch vụ đã dừng
                        listIdService = order.getServiceIds();
                        Iterator<Service> cusstomerInfoIterator = serviceArrayList.iterator();
                        while (cusstomerInfoIterator.hasNext()) {
                            Service id = cusstomerInfoIterator.next();
                            for (String idsv : listIdService) {
                                if (id.get_id().equals(idsv)) {
                                    sumprice+=id.getPrice();
                                    listnameseviceNew.add(id.getNameService());
                                    listIdServiceNew.add(id.get_id());
                                }
                            }

                        }
                      String[]  idservicenew = new String [listIdServiceNew.size()];
                        listIdServiceNew.toArray(idservicenew);
                        //end check
                        //check sumpricee
                        System.out.println(sumprice);
                        //end check
                        //start push data

                        if(idservicenew.length>0)
                        {
                            try {
                                Intent intent = new Intent(getApplicationContext(), DatlichActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("sumprice", sumprice);
                                bundle.putStringArray("listidservice", idservicenew);
                                bundle.putStringArrayList("listnameservice", listnameseviceNew);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }

                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Các dịch vụ bạn đặt đã không còn được phục vụ",Toast.LENGTH_SHORT).show();
                        }

                        //end activity
                    }
                });
                listView.setAdapter(adapterLichsu);
                if (orderList.size() > 0) {
                    btn_lichsucut_null.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("TAG", "onResponse: " + t);
            }
        });
    }

    private String token() {
        prefs = getSharedPreferences("HAIR", Context.MODE_PRIVATE);
        token = prefs.getString("token", toString());
        return token;
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
                    serviceArrayList.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                Log.e("LoiGETDATA", "onFailure: " + t);
            }
        });

    }

}