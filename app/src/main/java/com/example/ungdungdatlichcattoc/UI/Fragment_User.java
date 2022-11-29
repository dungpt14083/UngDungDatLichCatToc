package com.example.ungdungdatlichcattoc.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.ungdungdatlichcattoc.Adapter.Adapter_user;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.activity.AccoutActivity;
import com.example.ungdungdatlichcattoc.activity.LichSuCutActivity;
import com.example.ungdungdatlichcattoc.activity.LoginActivity;
import com.example.ungdungdatlichcattoc.activity.RepassActivity;
import com.example.ungdungdatlichcattoc.activity.ThongtTinHoTroKhActivity;
import com.example.ungdungdatlichcattoc.model.user;

import java.util.ArrayList;
import java.util.List;

public class Fragment_User extends Fragment {
    //    CardView crv_user_ThongTinTaiKhoan,crv_user_LichSuCut,crv_user_Repass,crv_user_thongtinhotrokhachhang;
    ListView listView;
    List<user> list;
    Adapter_user adapterUser;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        listView = view.findViewById(R.id.lv_danhsach);
        list = new ArrayList<>();
        list.add(new user(R.drawable.ic_vt_account_32, "Thông tin khách hàng", R.drawable.ic_vt_back_16));
        list.add(new user(R.drawable.ic_vt_time_32, "Lịch sử cắt", R.drawable.ic_vt_back_16));
        list.add(new user(R.drawable.ic_vt_info_32, "Thông tin hỗ trợ khách hàng", R.drawable.ic_vt_back_16));
        list.add(new user(R.drawable.ic_vt_map_32, "Vị trí Salon", R.drawable.ic_vt_back_16));
        list.add(new user(R.drawable.ic_vt_logout_32, "Đăng xuất", R.drawable.ic_vt_back_16));
        adapterUser = new Adapter_user(getContext(), list);
        listView.setAdapter(adapterUser);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(getContext(), AccoutActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getContext(), LichSuCutActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getContext(), ThongtTinHoTroKhActivity.class));
                        break;
                    case 3:
                        Toast.makeText(getContext(), i+"Vị trí Salon", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        logout();
                        Toast.makeText(getContext(), i+"Đăng xuất", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return view;
    }
    void logout(){
        SharedPreferences myPrefs = getActivity().getSharedPreferences("HAIR", Context.MODE_PRIVATE
                );
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.commit();
                //AppState.getSingleInstance().setLoggingOut(true);
        getActivity().finishAndRemoveTask();
                Intent intent = new Intent(getContext(),
                        LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
    }
}
