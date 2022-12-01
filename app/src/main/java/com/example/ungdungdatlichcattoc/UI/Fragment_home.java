package com.example.ungdungdatlichcattoc.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.os.Handler;
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
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.API.ApiCustomer;
import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_camket;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_image_slide;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_item_menu;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_traiNhiem;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.activity.Activity_map;
import com.example.ungdungdatlichcattoc.activity.Activity_newfeed;
import com.example.ungdungdatlichcattoc.activity.BanGiaActivity;
import com.example.ungdungdatlichcattoc.activity.DatlichActivity;
import com.example.ungdungdatlichcattoc.activity.LichSuCutActivity;
import com.example.ungdungdatlichcattoc.model.ProfileCus;
import com.example.ungdungdatlichcattoc.model.Service;
import com.example.ungdungdatlichcattoc.model.camket;
import com.example.ungdungdatlichcattoc.model.image;
import com.example.ungdungdatlichcattoc.model.itemmenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
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
    GridView grv_item,grv_camket;
    List<itemmenu> listItem;
    Adapter_item_menu adapter_item_menu;
    ViewPager viewPager;
    List<image> listImg;
    Timer timer;
    CircleIndicator circleIndicator;
    Adapter_image_slide image_slide;
    List<camket> listCamket;
    Adapter_camket camket;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getUserinfo();
        Profile(token);
        home_img_avt_user = view.findViewById(R.id.home_img_avt_user);
        grv_item = view.findViewById(R.id.grid_item);
        grv_camket = view.findViewById(R.id.grv_camket);
        home_tv_name_user = view.findViewById(R.id.home_tv_name_user);
        viewPager = view.findViewById(R.id.viewPager);
        imgNewFeed = view.findViewById(R.id.home_img_btn_newfeed);
        circleIndicator = view.findViewById(R.id.circleIndicator);
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
        getGridViewItemMenu();
        clickItem();
        setDataSlide();
        circleIndicator.setViewPager(viewPager);
        image_slide.registerDataSetObserver(circleIndicator.getDataSetObserver());
        AutoSlideImage();
        setDataCamket();
        return view;
    }
    void setDataCamket(){
        addDataCamKet();
        camket = new Adapter_camket(getContext(),listCamket);
        grv_camket.setAdapter(camket);
    }
    void addDataCamKet(){
        listCamket = new ArrayList<>();
        listCamket.add(new camket(R.drawable.ic_vt_check__32,"7 ngày","Chỉnh sửa tóc miễn phí"));
        listCamket.add(new camket(R.drawable.ic_vt_check__32,"30 ngày","Tư vấn / chăm sóc khách hàng"));
        listCamket.add(new camket(R.drawable.ic_vt_check__32,"15 ngày","Bảo hành uốn nhuộm"));
        listCamket.add(new camket(R.drawable.ic_vt_check__32,"Giảm 20%","Nếu chờ quá lâu"));

    }
    void setDataSlide(){
        addDataSlide();
        image_slide = new Adapter_image_slide(getContext(),listImg);
        viewPager.setAdapter(image_slide);
    }
    void addDataSlide(){
        listImg = new ArrayList<>();
        listImg.add(new image(R.drawable.slide0));
        listImg.add(new image(R.drawable.slide1));
        listImg.add(new image(R.drawable.slide2));
        listImg.add(new image(R.drawable.slide3));
    }
    private void AutoSlideImage() {
        if (listImg == null || listImg.isEmpty() || viewPager == null) {
            return;
        }
        // Init timer
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = listImg.size() - 1;
                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        }else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }

    void clickItem(){
        grv_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        startActivity(new Intent(getContext(), DatlichActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getContext(), LichSuCutActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getContext(), Activity_newfeed.class));
                        break;
                    case 3:
                        startActivity(new Intent(getContext(), Activity_map.class));
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
            }
        });
    }
    void getUserinfo() {
        prefs = getContext().getSharedPreferences("HAIR", getContext().MODE_PRIVATE);
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
        listItem.add(new itemmenu(R.drawable.ic_vt_time_32,"Lịch sử cắt"));
        listItem.add(new itemmenu(R.drawable.ic_vt_newr_32,"Bảng tin"));
        listItem.add(new itemmenu(R.drawable.ic_vt_location_32,"Vị trí Salon"));
        listItem.add(new itemmenu(R.drawable.ic_vt_cut_32,"Bí kíp chăm sóc tóc"));
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
                    Glide.with(getContext()).load("http://io.supermeo.com:8000/" + profileCus.getImage()).into(home_img_avt_user);
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
