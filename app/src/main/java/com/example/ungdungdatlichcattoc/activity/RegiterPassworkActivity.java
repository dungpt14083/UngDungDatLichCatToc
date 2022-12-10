package com.example.ungdungdatlichcattoc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.Utils;
import com.example.ungdungdatlichcattoc.model.RegisterResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegiterPassworkActivity extends AppCompatActivity {
    TextInputEditText edt_regiterPass_pass;
    AppCompatButton button_Regiter;
    SharedPreferences prefs;
    String phone, pass,mtopicId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPhone();
        setContentView(com.example.ungdungdatlichcattoc.R.layout.activity_regiter_passwork);
        edt_regiterPass_pass = findViewById(R.id.edt_regiterPass_pass);
        button_Regiter = findViewById(R.id.button_Regiter);
        regiterTopic();

        mtopicId =prefs.getString("phone", toString());
        button_Regiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pass = edt_regiterPass_pass.getText().toString();
                try {
                    if (check()) {
                        register(phone, pass,mtopicId,"Đăng Ký Thành Công","Chào Mừng bạn tới với Happy Baber Shop");
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
private void getPhone(){
    prefs = getSharedPreferences("Register", MODE_PRIVATE);

    phone = prefs.getString("phone", toString());
}
    private Boolean check() {
        Boolean x = true;
        if (edt_regiterPass_pass.getText().toString().trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn không được bỏ trống mật khẩu", Toast.LENGTH_SHORT).show();
            x = false;
        }
        if (edt_regiterPass_pass.getText().toString().trim().length() <= 6) {
            Toast.makeText(getApplicationContext(), "Mật khẩu của bạn cần nhiều hơn 6 kí tự", Toast.LENGTH_SHORT).show();
            x = false;
        }
        return x;
    }
    private void regiterTopic(){
        FirebaseMessaging.getInstance().subscribeToTopic(prefs.getString("phone", toString()))
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }
                        Log.d("TAG", msg);
                        // Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void register(String phone, String password,String topicId,String body,String title) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/customer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);
        Call<RegisterResponse> call = api.register(phone, password,topicId,body,title);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(RegiterPassworkActivity.this, getString(R.string.register_successfully), Toast.LENGTH_SHORT).show();
                        finishAndRemoveTask();
                        Intent intent = new Intent(RegiterPassworkActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegiterPassworkActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.d("go", "onFailure: " + t.toString());
            }
        });
    }
}