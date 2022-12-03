package com.example.ungdungdatlichcattoc.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.Adapter.Adapter_InforUserSupport;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.InforUserSupport;

import java.util.ArrayList;
import java.util.List;

public class ThongtTinHoTroKhActivity extends AppCompatActivity {
    List<InforUserSupport> userSupportList;
    Adapter_InforUserSupport adapter_inforUserSupport;
    ListView lv_danhsach_UserSupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongt_tin_ho_tro_kh);
        lv_danhsach_UserSupport = findViewById(R.id.lv_danhsach_UserSupport);
        userSupportList = new ArrayList<>();
        userSupportList.add(new InforUserSupport(R.drawable.ic_vt_round_24, "Cam kết về 30 shine care", R.drawable.ic_vt_back_16));
        userSupportList.add(new InforUserSupport(R.drawable.ic_vt_people24, "Về chúng tôi", R.drawable.ic_vt_back_16));
        userSupportList.add(new InforUserSupport(R.drawable.ic_vt_sync_24, "Điều kiện giao dịch chung", R.drawable.ic_vt_back_16));
        userSupportList.add(new InforUserSupport(R.drawable.ic_vt_admin_24, "Chính sách bảo mật thông tin", R.drawable.ic_vt_back_16));
        adapter_inforUserSupport = new Adapter_InforUserSupport(this, userSupportList);
        lv_danhsach_UserSupport.setAdapter(adapter_inforUserSupport);
        lv_danhsach_UserSupport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Toast.makeText(ThongtTinHoTroKhActivity.this, i + "Cam kết về 30 shine care", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(ThongtTinHoTroKhActivity.this, i + "Về chúng tôi", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        startActivity(new Intent(ThongtTinHoTroKhActivity.this, Activity_DieuKienGiaoDichChung.class));
                        break;
                    case 3:
                        startActivity(new Intent(ThongtTinHoTroKhActivity.this, ChinhSachBaoMat.class));
                        break;

                }
            }
        });
    }

}