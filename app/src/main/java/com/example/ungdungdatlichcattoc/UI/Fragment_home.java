package com.example.ungdungdatlichcattoc.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.activity.Activity_newfeed;
import com.example.ungdungdatlichcattoc.activity.BanGiaActivity;
import com.example.ungdungdatlichcattoc.activity.DatlichActivity;
import com.example.ungdungdatlichcattoc.activity.LichSuCutActivity;

public class Fragment_home extends Fragment {
    CardView btnDatLich,btnBangGia,btnLichSuCut;
    ImageView imgNewFeed ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home,container,false);

        btnDatLich = view.findViewById(R.id.home_cardview_datlich);
        btnBangGia=view.findViewById(R.id.home_cardview_banggia);
        btnLichSuCut = view.findViewById(R.id.home_cardview_lichsucut);
        imgNewFeed = view.findViewById(R.id.home_img_btn_newfeed);
        imgNewFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNewfeed = new Intent(getActivity(), Activity_newfeed .class);
                startActivity(intentNewfeed);
            }
        });
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
