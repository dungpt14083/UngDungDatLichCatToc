package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.API.ApiCustomer;
import com.example.ungdungdatlichcattoc.MainActivity;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.Utils;
import com.example.ungdungdatlichcattoc.model.ProfileCus;
import com.example.ungdungdatlichcattoc.model.RepassRespone;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepassActivity extends AppCompatActivity {

    EditText edt_repass_pass, edt_repass_repass;
    AppCompatButton button_repass;
    SharedPreferences prefs;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repass);
        edt_repass_pass = findViewById(R.id.edt_repass_pass);
        edt_repass_repass = findViewById(R.id.edt_repass_repass);
        button_repass = findViewById(R.id.button_repass);
        getUserinfo();
        button_repass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = edt_repass_pass.getText().toString().trim();
                String passwordConf = edt_repass_repass.getText().toString().trim();

                List<EditText> list = new ArrayList<>();
                list.add(edt_repass_pass);
                list.add(edt_repass_repass);

                if (!Utils.checkValidate(list)) {
                    return;
                }
                if (password.equals(passwordConf)) {
                    try {
                        if (check(password)) {
                            Repass(token, password);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Mật khẩu Không Trùng Khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean check(String pass) {
        Boolean x = true;
        if (pass.trim().length() <= 6) {
            Toast.makeText(getApplicationContext(), "Mật khẩu phải trên 6 kí tự,không tính dấu cách", Toast.LENGTH_SHORT).show();
            x = false;
        }
        return x;

    }

    void getUserinfo() {
        prefs = getSharedPreferences("HAIR", MODE_PRIVATE);
        token = prefs.getString("token", token);

    }

    private void Repass(String token, String pass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/customer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiCustomer apiCustomer = retrofit.create(ApiCustomer.class);
        Call<RepassRespone> call = apiCustomer.REPASS_RESPONE_CALL(token, pass);
        call.enqueue(new Callback<RepassRespone>() {
            @Override
            public void onResponse(Call<RepassRespone> call, Response<RepassRespone> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(getApplicationContext(), "Đổi Mật Khẩu Thành Công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RepassActivity.this, MainActivity.class);
                        startActivity(intent);


                    } else {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RepassRespone> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}