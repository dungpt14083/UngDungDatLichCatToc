package com.example.ungdungdatlichcattoc.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_traiNhiem;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.activity.BanGiaActivity;
import com.example.ungdungdatlichcattoc.activity.DatlichActivity;
import com.example.ungdungdatlichcattoc.activity.LichSuCutActivity;
import com.example.ungdungdatlichcattoc.model.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_home extends Fragment {
    CardView btnDatLich,btnBangGia,btnLichSuCut;
    ListView lv ;

    private List<Service> serviceList1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home,container,false);

        btnDatLich = view.findViewById(R.id.home_cardview_datlich);
        btnBangGia=view.findViewById(R.id.home_cardview_banggia);
        btnLichSuCut = view.findViewById(R.id.home_cardview_lichsucut);
        btnBangGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , BanGiaActivity.class);
                startActivity(intent);
            }
        });

        lv = view.findViewById(R.id.home_rcv_dichvu) ;
        serviceList1 = new ArrayList<>();
        Adapter_traiNhiem adapter_traiNhiem = new Adapter_traiNhiem(getContext(), R.layout.item_trai_nhiem, serviceList1 ) ;

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
                    Toast.makeText(getContext(), "Update Data Successfull", Toast.LENGTH_SHORT).show();
                    lv.setAdapter(adapter_traiNhiem);
                    adapter_traiNhiem.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi rồi ông cháu: " + t, Toast.LENGTH_SHORT).show();
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
        btnDatLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDatLich = new Intent(getActivity(), DatlichActivity.class);
                startActivity(intentDatLich);
            }
        });
        btnBangGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBangGia = new Intent(getActivity(), BanGiaActivity.class);
                startActivity(intentBangGia);
            }
        });
        btnLichSuCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenLichSuCut = new Intent(getActivity(), LichSuCutActivity.class);
                startActivity(intenLichSuCut);
            }
        });
        return view;
    }
}
