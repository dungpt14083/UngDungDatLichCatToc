package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.API.ApiCustomer;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.ProfileCus;
import com.google.android.material.imageview.ShapeableImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccoutActivity extends AppCompatActivity {
    private TextView tv_account_birthday, tv_account_phone, tv_account_nameuser, tv_account_update, tv_account_adress, tv_account_logout;
    private String nameUser, token, adress;
    private String birThday;
    private String Phone;
    SharedPreferences prefs;
    List<ProfileCus> cusstomerInfoList;
    ShapeableImageView img_account_avat;
    ImageView btn_back_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accout);
        cusstomerInfoList = new ArrayList<>();
        tv_account_nameuser = findViewById(R.id.tv_account_nameuser);
        tv_account_birthday = findViewById(R.id.tv_account_birthday);
        tv_account_phone = findViewById(R.id.tv_account_phone);
        tv_account_update = findViewById(R.id.tv_account_update);
        tv_account_adress = findViewById(R.id.tv_account_adress);
        img_account_avat =findViewById(R.id.img_account_avat);
        btn_back_user = findViewById(R.id.btn_back_user);
//        tv_account_logout =findViewById(R.id.tv_account_logout);
        tv_account_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccoutActivity.this, UpdateAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_back_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
//        tv_account_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences myPrefs = getSharedPreferences("HAIR",
//                        MODE_PRIVATE);
//                SharedPreferences.Editor editor = myPrefs.edit();
//                editor.clear();
//                editor.commit();
//                //AppState.getSingleInstance().setLoggingOut(true);
//                Intent intent = new Intent(AccoutActivity.this,
//                        LoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                // onDestroy();
//
//            }
//        });
        getUserinfo();
        Profile(token);

    }

    void getUserinfo() {
        prefs = getSharedPreferences("HAIR", MODE_PRIVATE);
        token = prefs.getString("token", toString());
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
                    nameUser = profileCus.getNameUser();
                    birThday = profileCus.getBirthOfYear();
                    Phone = profileCus.getPhone();
                    adress = profileCus.getAddress();
                    Log.e("loidate", "onResponse: "+birThday );

                    tv_account_nameuser.setText(nameUser);
                    tv_account_adress.setText(adress);
                    tv_account_phone.setText("+84 " +Phone);
                    Glide.with(AccoutActivity.this).load("http://io.supermeo.com:8000/"+profileCus.getImage()).into(img_account_avat);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date datebirth = format.parse(birThday);

                        tv_account_birthday.setText(format.format(datebirth));
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
//
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
