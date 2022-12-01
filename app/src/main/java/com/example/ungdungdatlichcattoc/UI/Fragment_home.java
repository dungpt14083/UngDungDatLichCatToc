package com.example.ungdungdatlichcattoc.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.API.ApiCustomer;
import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_item_menu;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_traiNhiem;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.activity.Activity_newfeed;
import com.example.ungdungdatlichcattoc.activity.BanGiaActivity;
import com.example.ungdungdatlichcattoc.activity.DatlichActivity;
import com.example.ungdungdatlichcattoc.activity.LichSuCutActivity;
import com.example.ungdungdatlichcattoc.model.ProfileCus;
import com.example.ungdungdatlichcattoc.model.Service;
import com.example.ungdungdatlichcattoc.model.itemmenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_home extends Fragment {
//    CardView btnDatLich, btnBangGia, btnLichSuCut;
    ImageView imgNewFeed;
    ListView lv;
    SharedPreferences prefs;
    String token;
    ImageView home_img_avt_user;
    private List<Service> serviceList1;
    TextView home_tv_name_user;
    GridView grv_item;
    List<itemmenu> listItem;
    Adapter_item_menu adapter_item_menu;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getUserinfo();
        Profile(token);
//        btnDatLich = view.findViewById(R.id.home_cardview_datlich);
//        btnBangGia = view.findViewById(R.id.home_cardview_banggia);
        home_img_avt_user = view.findViewById(R.id.home_img_avt_user);
//        btnLichSuCut = view.findViewById(R.id.home_cardview_lichsucut);
        grv_item = view.findViewById(R.id.grid_item);
        home_tv_name_user = view.findViewById(R.id.home_tv_name_user);
//        btnBangGia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), BanGiaActivity.class);
//                startActivity(intent);
//            }
//        });

        imgNewFeed = view.findViewById(R.id.home_img_btn_newfeed);
        imgNewFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_newfeed.class);
                startActivity(intent);
            }
        });
        lv = view.findViewById(R.id.home_rcv_dichvu);
        serviceList1 = new ArrayList<>();
        Adapter_traiNhiem adapter_traiNhiem = new Adapter_traiNhiem(getContext(), R.layout.item_trai_nhiem, serviceList1);

        lv.setAdapter(adapter_traiNhiem);
        //
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
                    serviceList1.addAll(response.body());
                    lv.setAdapter(adapter_traiNhiem);
                    adapter_traiNhiem.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                Log.e("LoiGETDATA", "onFailure: " + t);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), BanGiaActivity.class);
                startActivity(intent);
            }
        });
        //
//        btnDatLich.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intentDatLich = new Intent(getActivity(), DatlichActivity.class);
//                startActivity(intentDatLich);
//            }
//        });
//        btnBangGia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intentBangGia = new Intent(getActivity(), BanGiaActivity.class);
//                startActivity(intentBangGia);
//            }
//        });
//        btnLichSuCut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intenLichSuCut = new Intent(getActivity(), LichSuCutActivity.class);
//                startActivity(intenLichSuCut);
//            }
//        });
        getGridViewItemMenu();
        clickItem();
        return view;
    }
    void clickItem(){
        grv_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "abc" + i, Toast.LENGTH_SHORT).show();
            }
        });
    }
    void getUserinfo() {
        prefs = getActivity().getSharedPreferences("HAIR", getActivity().MODE_PRIVATE);
        token = prefs.getString("token", toString());
    }
    void getGridViewItemMenu(){
        listItem = new ArrayList<>();
        addDataItem();
        adapter_item_menu = new Adapter_item_menu(getContext(),listItem);
        grv_item.setAdapter(adapter_item_menu);
    }
    void addDataItem(){
        listItem.add(new itemmenu(R.drawable.ic_vt_date_32,"Đặt lịch"));
        listItem.add(new itemmenu(R.drawable.ic_vt_date_32,"Lịch sử cắt"));
        listItem.add(new itemmenu(R.drawable.ic_vt_date_32,"Bảng tin"));
        listItem.add(new itemmenu(R.drawable.ic_vt_date_32,"Vị trí Salon"));
        listItem.add(new itemmenu(R.drawable.ic_vt_date_32,"Vị trí Salon"));
        listItem.add(new itemmenu(R.drawable.ic_vt_date_32,"Vị trí Salon"));
    }
    private void Profile(String customerId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/customer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiCustomer apiCustomer = retrofit.create(ApiCustomer.class);
        Call<ProfileCus> call = apiCustomer.Getprofile(customerId);
        call.enqueue(new Callback<ProfileCus>() {
            @Override
            public void onResponse(Call<ProfileCus> call, Response<ProfileCus> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //   cusstomerInfoList.addAll(response.body());
                    // Log.e("TAGsize", "onResponse: " + cusstomerInfoList.size());
                    ProfileCus profileCus = response.body();
                    try {
                        home_tv_name_user.setText(profileCus.getNameUser().toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Glide.with(getActivity()).load("http://io.supermeo.com:8000/" + profileCus.getImage()).into(home_img_avt_user);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {


                    } catch (Exception e) {

                    }
                    //    getdatatv();
                } else {
                    Log.e("loi", "onResponse: looix");
                }
            }

            @Override
            public void onFailure(Call<ProfileCus> call, Throwable t) {
                //    Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.e("loi", "onResponse: looix " + t.getMessage());
            }
        });
    }
}
