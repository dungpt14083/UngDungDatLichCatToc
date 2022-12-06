package com.example.ungdungdatlichcattoc.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.MainActivity;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.Utils;
import com.example.ungdungdatlichcattoc.model.LoginResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText editTextPhone, editTextPassword;
    Button buttonLogin;
    TextView textViewRegister,forget_password;
    String token =null;
    String phoneauto,passauto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_2);

        forget_password=findViewById(R.id.forget_password);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        textViewRegister = findViewById(R.id.textViewRegister);

         try {
             SharedPreferences sharedPreferences = getSharedPreferences("HAIR",MODE_PRIVATE);

             phoneauto = sharedPreferences.getString("phone","");
             passauto =sharedPreferences.getString("password","");
             if(phoneauto.trim().length()>0&&passauto.trim().length()>0){
                 loginUser(phoneauto,passauto);
             }
             else {
                 Toast.makeText(getApplicationContext(),"Vui Lòng Đăng Nhập",Toast.LENGTH_SHORT).show();
             }
         }catch (Exception e){

         }


        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,QuenMatKhauActivity.class);
                startActivity(intent);
            }
        });
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegiterPhoneActivity.class));
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = editTextPhone.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                List<EditText> list = new ArrayList<>();
                list.add(editTextPhone);
                list.add(editTextPassword);
                if (!Utils.checkValidate(list)) {
                    return;
                }
                if(checklogin(phone,password)==true){
                    loginUser(phone, password);
                }
            }
        });

    }
    public static boolean isValidMobileNo(String str)
    {
//(0/91): number starts with (0/91)
//[7-9]: starting of the number may contain a digit between 0 to 9
//[0-9]: then contains digits 0 to 9
        Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
//the matcher() method creates a matcher that will match the given input against this pattern
        Matcher match = ptrn.matcher(str);
//returns a boolean value
        return (match.find() && match.group().equals(str));
    }
    private boolean checklogin(String phone,String password){
        boolean x =true;


        return x;

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
                        editor.putString("token",token);
                        editor.putString("birthday",loginResponse.getBirthOfYear());
                        editor.putString("nameUser",loginResponse.getNameUser());
                        editor.putString("address",loginResponse.getAddress());

                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Hãy Thử Đăng Nhập Lại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this,"Hãy Thử Đăng Nhập Lại", Toast.LENGTH_SHORT).show();
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


