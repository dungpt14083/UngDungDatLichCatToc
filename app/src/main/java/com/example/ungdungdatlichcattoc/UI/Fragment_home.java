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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.API.ApiCustomer;
import com.example.ungdungdatlichcattoc.API.ApiOrder;
import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_camket;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_image_slide;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_item_menu;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_topstylish;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_traiNhiem;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.activity.AccoutActivity;
import com.example.ungdungdatlichcattoc.activity.Activity_newfeed;
import com.example.ungdungdatlichcattoc.activity.BangGiaActivity;
import com.example.ungdungdatlichcattoc.activity.DatlichActivity;
import com.example.ungdungdatlichcattoc.activity.LichSuCutActivity;
import com.example.ungdungdatlichcattoc.activity.MapGGActivity;
import com.example.ungdungdatlichcattoc.model.ProfileCus;
import com.example.ungdungdatlichcattoc.model.Service;
import com.example.ungdungdatlichcattoc.model.TopStylish;
import com.example.ungdungdatlichcattoc.model.camket;
import com.example.ungdungdatlichcattoc.model.image;
import com.example.ungdungdatlichcattoc.model.itemmenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_home extends Fragment {
    //    CardView btnDatLich, btnBangGia, btnLichSuCut;
    ImageView imgNewFeed, home_img_btn_discorver;
    ListView lv,home_rcv_hotstylish;
    SharedPreferences prefs;
    String token,userName,Imageavt;
    CircleImageView home_img_avt_user;
    private List<Service> serviceList1;
    TextView home_tv_name_user;
    GridView grv_item, grv_camket;
    List<itemmenu> listItem;
    Adapter_item_menu adapter_item_menu;
    ViewPager viewPager;
    List<image> listImg;
    Timer timer;
    CircleIndicator circleIndicator;
    Adapter_image_slide image_slide;
    List<camket> listCamket;
    Adapter_camket camket;
    List<TopStylish> topStylishList ;

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
        home_img_btn_discorver = view.findViewById(R.id.home_img_btn_discorver);
        circleIndicator = view.findViewById(R.id.circleIndicator);
        home_rcv_hotstylish=view.findViewById(R.id.home_rcv_hotstylish);
        topStylishList= new ArrayList<>();
        home_img_btn_discorver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_newfeed.class);
                startActivity(intent);
            }
        });
        home_img_avt_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AccoutActivity.class);
                startActivity(intent);
            }
        });
        imgNewFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        Adapter_topstylish adapter_topstylish = new Adapter_topstylish(getContext(),R.layout.item_hotstylish,topStylishList);
        home_rcv_hotstylish.setAdapter(adapter_topstylish);
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
                Intent intent = new Intent(getContext(), BangGiaActivity.class);
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
        gettopstylish();

        return view;
    }
    void gettopstylish(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/order/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiOrder apiOrder = retrofit.create(ApiOrder.class);
        Call<List<TopStylish>> callservice = apiOrder.getTopHairStylish();
        callservice.enqueue(new Callback<List<TopStylish>>() {
            @Override
            public void onResponse(Call<List<TopStylish>> call, Response<List<TopStylish>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    topStylishList.addAll(response.body());
                    Adapter_topstylish adapter_topstylish = new Adapter_topstylish(getContext(),R.layout.item_hotstylish,topStylishList);
                    home_rcv_hotstylish.setAdapter(adapter_topstylish);
                    adapter_topstylish.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<TopStylish>> call, Throwable t) {
                Log.e("LoiGETDATA", "onFailure: " + t);
            }
        });
    }

    void setDataCamket() {
        addDataCamKet();
        camket = new Adapter_camket(getContext(), listCamket);
        grv_camket.setAdapter(camket);
    }

    void addDataCamKet() {
        listCamket = new ArrayList<>();
        listCamket.add(new camket(R.drawable.ic_vt_check__32, "7 ngày", "Chỉnh sửa tóc miễn phí"));
        listCamket.add(new camket(R.drawable.ic_vt_check__32, "30 ngày", "Tư vấn / chăm sóc khách hàng"));
        listCamket.add(new camket(R.drawable.ic_vt_check__32, "15 ngày", "Bảo hành uốn nhuộm"));
        listCamket.add(new camket(R.drawable.ic_vt_check__32, "Giảm 20%", "Nếu chờ quá lâu"));

    }

    void setDataSlide() {
        addDataSlide();
        image_slide = new Adapter_image_slide(getContext(), listImg);
        viewPager.setAdapter(image_slide);
    }

    void addDataSlide() {
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
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }

    void clickItem() {
        grv_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(getContext(), DatlichActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getContext(), LichSuCutActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getContext(), BangGiaActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getContext(), MapGGActivity.class));
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
        userName=prefs.getString("nameUser",toString());
        Imageavt =prefs.getString("image",toString());
    }

    void getGridViewItemMenu() {
        listItem = new ArrayList<>();
        addDataItem();
        adapter_item_menu = new Adapter_item_menu(getContext(), listItem);
        grv_item.setAdapter(adapter_item_menu);
    }

    void addDataItem() {
        listItem.add(new itemmenu(R.drawable.ic_vt_date_32, "Đặt lịch"));
        listItem.add(new itemmenu(R.drawable.ic_vt_time_32, "Lịch sử cắt"));
        listItem.add(new itemmenu(R.drawable.ic_vt_newr_32, "Bảng giá"));
        listItem.add(new itemmenu(R.drawable.ic_vt_location_32, "Vị trí Salon"));
        listItem.add(new itemmenu(R.drawable.ic_vt_cut_32, "Bí kíp chăm sóc tóc"));
        listItem.add(new itemmenu(R.drawable.ic_vt_date_32, "Vị trí Salon"));
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
                        home_tv_name_user.setText(profileCus.getNameUser());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Glide.with(getContext()).load("http://io.supermeo.com:8000/" +profileCus.getImage()).into(home_img_avt_user);

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
