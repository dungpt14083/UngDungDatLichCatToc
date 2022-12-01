package com.example.ungdungdatlichcattoc.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.Adapter.SliderPageAdapter;
import com.example.ungdungdatlichcattoc.MainActivity;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.UI.Fragment_Page1;
import com.example.ungdungdatlichcattoc.UI.Fragment_Page2;
import com.example.ungdungdatlichcattoc.model.LoginResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity {
    private ViewPager viewPager2;
    private SliderPageAdapter pageAdapter;
    String token;
     ProgressDialog SplashmprogressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        viewPager2 = findViewById(R.id.viewPage2_splash);
        SplashmprogressDialog = new ProgressDialog(this);
        SplashmprogressDialog.setMessage("...");
        List<Fragment> mList = new ArrayList<>();
        mList.add(new Fragment_Page1());
        mList.add(new Fragment_Page2());

        pageAdapter = new SliderPageAdapter(getSupportFragmentManager(), mList);
        viewPager2.setAdapter(pageAdapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    SharedPreferences sharedPreferences = getSharedPreferences("HAIR", MODE_PRIVATE);
                    String phoneauto = sharedPreferences.getString("phone", "");
                    String passauto = sharedPreferences.getString("password", "");
                    if (phoneauto.trim().length() > 0 && passauto.trim().length() > 0) {
                        SplashmprogressDialog.show();
                        loginUser(phoneauto, passauto);
                    } else {
                        SplashmprogressDialog.show();
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {

                }

            }
        }, 3000);


    }

    private void loginUser(String phone, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/customer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);
        Call<LoginResponse> call = api.login(phone, password, getUniqueId());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        LoginResponse loginResponse = response.body();
                        token = loginResponse.getToken();
                        SharedPreferences.Editor editor = getSharedPreferences("HAIR", MODE_PRIVATE).edit();
                        editor.putString("phone", phone);
                        editor.putString("password", password);
                        editor.putString("id", response.body().getId());
                        editor.putString("token", token);
                        editor.putString("birthday", loginResponse.getBirthOfYear());
                        editor.putString("nameUser", loginResponse.getNameUser());
                        editor.putString("address", loginResponse.getAddress());

                        editor.apply();
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(SplashActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SplashActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("TAG", "onFailure: " + "Throw" + t.toString());
            }
        });

    }

    @SuppressLint("HardwareIds")
    private String getUniqueId() {
        return Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}