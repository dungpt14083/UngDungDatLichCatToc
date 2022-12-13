package com.example.ungdungdatlichcattoc.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.API.ApiCustomer;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_InforUserSupport;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_user;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.activity.AccoutActivity;
import com.example.ungdungdatlichcattoc.activity.LichSuCutActivity;
import com.example.ungdungdatlichcattoc.activity.LoginActivity;
import com.example.ungdungdatlichcattoc.activity.MapGGActivity;
import com.example.ungdungdatlichcattoc.activity.RepassActivity;
import com.example.ungdungdatlichcattoc.activity.ThongtTinHoTroKhActivity;
import com.example.ungdungdatlichcattoc.model.InforUserSupport;
import com.example.ungdungdatlichcattoc.model.ProfileCus;
import com.example.ungdungdatlichcattoc.model.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_User extends Fragment {
    //    CardView crv_user_ThongTinTaiKhoan,crv_user_LichSuCut,crv_user_Repass,crv_user_thongtinhotrokhachhang;
    ListView listView;
    List<user> list;
    Adapter_user adapterUser;
    SharedPreferences prefs;
    String token, nameUser, avatar;
    CircleImageView user_img_avt_user;
    TextView user_tv_name_user;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        listView = view.findViewById(R.id.lv_danhsach);
        user_img_avt_user = view.findViewById(R.id.user_img_avt_user);
        user_tv_name_user = view.findViewById(R.id.user_tv_name_user);
        list = new ArrayList<>();
        list.add(new user(R.drawable.ic_vt_account_32, "Thông tin khách hàng", R.drawable.ic_vt_back_16));
        list.add(new user(R.drawable.ic_vt_time_32, "Lịch sử cắt", R.drawable.ic_vt_back_16));
        list.add(new user(R.drawable.ic_vt_info_32, "Thông tin hỗ trợ khách hàng", R.drawable.ic_vt_back_16));
        list.add(new user(R.drawable.ic_vt_key, "Đổi Mật Khẩu", R.drawable.ic_vt_back_16));
        list.add(new user(R.drawable.ic_vt_map_32, "Vị trí Salon", R.drawable.ic_vt_back_16));
        list.add(new user(R.drawable.ic_vt_logout_32, "Đăng xuất", R.drawable.ic_vt_back_16));
        adapterUser = new Adapter_user(getContext(), list);
        listView.setAdapter(adapterUser);
        //---------------------

        getUserinfo();
        try {
            user_tv_name_user.setText(nameUser);
            Glide.with(getActivity()).load("http://io.supermeo.com:8000/" + avatar).into(user_img_avt_user);


        } catch (Exception e) {
            e.printStackTrace();
        }
       // Profile(token);
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
                        startActivity(new Intent(getContext(), RepassActivity.class));

                        break;
                    case 4:
                        startActivity(new Intent(getContext(), MapGGActivity.class));
                        break;
                    case 5:
                        logout();
                        Toast.makeText(getContext(), "Đăng xuất", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return view;
    }

    void getUserinfo() {
        prefs = getActivity().getSharedPreferences("HAIR", getActivity().MODE_PRIVATE);
        token = prefs.getString("token", toString());
        nameUser = prefs.getString("nameUser", null);
        if(nameUser==null){
            nameUser="GUEST";
        }
        avatar = prefs.getString("imageAvatar", toString());
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
                         user_tv_name_user.setText(profileCus.getNameUser());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Glide.with(getActivity()).load("http://io.supermeo.com:8000/" + profileCus.getImage()).into(user_img_avt_user);

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

    void logout() {
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
