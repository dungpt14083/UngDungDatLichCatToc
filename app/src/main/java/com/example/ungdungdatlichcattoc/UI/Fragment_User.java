package com.example.ungdungdatlichcattoc.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.activity.AccoutActivity;
import com.example.ungdungdatlichcattoc.activity.LichSuCutActivity;
import com.example.ungdungdatlichcattoc.activity.RepassActivity;
import com.example.ungdungdatlichcattoc.activity.ThongtTinHoTroKhActivity;

public class Fragment_User extends Fragment {
    CardView crv_user_ThongTinTaiKhoan,crv_user_LichSuCut,crv_user_Repass,crv_user_thongtinhotrokhachhang;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_user,container,false);
        crv_user_ThongTinTaiKhoan = view.findViewById(R.id.crv_user_ThongTinTaiKhoan);
        crv_user_LichSuCut =view.findViewById(R.id.crv_user_LichSuCut);
        crv_user_Repass =view.findViewById(R.id.crv_user_Repass);
        crv_user_thongtinhotrokhachhang=view.findViewById(R.id.crv_user_thongtinhotrokhachhang);
        crv_user_LichSuCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LichSuCutActivity.class);
                startActivity(intent);
            }
        });
        crv_user_ThongTinTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AccoutActivity.class);
              startActivity(intent);
            }
        });
        crv_user_Repass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RepassActivity.class);
                startActivity(intent);
            }
        });
        crv_user_thongtinhotrokhachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ThongtTinHoTroKhActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
