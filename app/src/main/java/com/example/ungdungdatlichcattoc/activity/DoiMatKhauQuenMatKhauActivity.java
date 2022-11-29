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
import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.CusstomerInfo;
import com.example.ungdungdatlichcattoc.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoiMatKhauQuenMatKhauActivity extends AppCompatActivity {
    EditText edt_fogot_repass_repass, edt_fogot_repass_pass;
    AppCompatButton Btn_fogot;
    SharedPreferences prefs;
    String phone, pass, pascf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau_quen_mat_khau);
        edt_fogot_repass_repass = findViewById(R.id.edt_fogot_repass_repass);
        edt_fogot_repass_pass = findViewById(R.id.edt_fogot_repass_pass);
        Btn_fogot = findViewById(R.id.button_fogot_repass);
        Btn_fogot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefs = getSharedPreferences("Fogot", MODE_PRIVATE);
                phone = prefs.getString("phone", toString());
                pass = edt_fogot_repass_pass.getText().toString();
                pascf = edt_fogot_repass_repass.getText().toString();
                if (pass.equals(pascf)) {
                    if (check()) {
                        FogotPass(phone, pass);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.clear();
                    }

                }
            }
        });

    }

    private Boolean check() {
        Boolean x = true;
        if (edt_fogot_repass_pass.getText().toString().trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn không được bỏ trống mật khẩu", Toast.LENGTH_SHORT).show();
            x = false;
        }
        if (edt_fogot_repass_repass.getText().toString().trim().length() <= 6) {
            Toast.makeText(getApplicationContext(), "Mật khẩu của bạn cần nhiều hơn 6 kí tự", Toast.LENGTH_SHORT).show();
            x = false;
        }
        return x;
    }

    private void FogotPass(String phone, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/customer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiCustomer api = retrofit.create(ApiCustomer.class);
        Call<CusstomerInfo> call = api.FOR_RESPONE_CALL(phone, password);
        call.enqueue(new Callback<CusstomerInfo>() {
            @Override
            public void onResponse(Call<CusstomerInfo> call, Response<CusstomerInfo> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(getApplicationContext(), "Thay Đổi Mật Khẩu Thành Công", Toast.LENGTH_SHORT).show();
                        finishAndRemoveTask();
                        Intent intent = new Intent(DoiMatKhauQuenMatKhauActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<CusstomerInfo> call, Throwable t) {

            }
        });
    }
}