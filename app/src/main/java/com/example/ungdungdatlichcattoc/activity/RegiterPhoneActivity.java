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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.Utils;
import com.example.ungdungdatlichcattoc.model.CartService;
import com.example.ungdungdatlichcattoc.model.CusstomerInfo;
import com.example.ungdungdatlichcattoc.model.LoginResponse;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegiterPhoneActivity extends AppCompatActivity {
    EditText editTextPhone;
    RadioButton radioButtonxacnhan;
    AppCompatButton btn_continue;
    ImageView imgback;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    List<CusstomerInfo> loginResponseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiter_phone);
        mAuth = FirebaseAuth.getInstance();
        editTextPhone = findViewById(R.id.edt_regiterphone_sdt);
        radioButtonxacnhan = findViewById(R.id.radiodieukhoan);
        btn_continue = findViewById(R.id.btn_regiterphone_continue);
        imgback = findViewById(R.id.Img_regerterphone_homback);
        loginResponseList = new ArrayList<>();
        getallUser();
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegiterPhoneActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = editTextPhone.getText().toString().trim();
                List<EditText> list = new ArrayList<>();
                list.add(editTextPhone);
                if (!Utils.checkValidate(list)) {
                    return;
                }
                if (check(phone)) {
                    if(checkphone(phone)){
                        SharedPreferences.Editor editor = getSharedPreferences("Register", MODE_PRIVATE).edit();
                        editor.putString("phone", phone);

                        editor.apply();

                        otpsend(phone);
                        Intent intent = new Intent(RegiterPhoneActivity.this, XacthucSDT.class);
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Hãy Nhập Đúng Số Điện Thoại", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private boolean checkphone(String phone) {
        boolean x= true;

//        CusstomerInfo loginResponse1 = loginResponseList.get(5);
//        Toast.makeText(getApplicationContext(), loginResponse1.getPhone(), Toast.LENGTH_SHORT).show();
//        for (int i = 0; i < loginResponseList.size(); i++) {
//
//            System.out.println("phoneset: "+ phone.substring(1));
//            System.out.println("phoneset: "+ loginResponseList.get(i).getPhone());
//            if ( loginResponseList.get(i).getPhone().equalsIgnoreCase(phone.substring(1))) {
//                Toast.makeText(getApplicationContext(), "Số điện thoại đã được sử dụng", Toast.LENGTH_SHORT).show();
//                x= false;
//
//            } else {
//                Toast.makeText(getApplicationContext(), "Số điện thoại ", Toast.LENGTH_SHORT).show();
//                x= true;
//            }
//        }
        Iterator<CusstomerInfo> cusstomerInfoIterator = loginResponseList.iterator();
        while (cusstomerInfoIterator.hasNext()) {
            CusstomerInfo id = cusstomerInfoIterator.next();
            if (id.getPhone().equals(phone.substring(1))) {
                Toast.makeText(getApplicationContext(), "Số điện thoại đã được sử dụng", Toast.LENGTH_SHORT).show();
                x=false;
            }
        }
        return x;
    }



    void getallUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/customer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<CusstomerInfo>> callallUser = apiService.getallUser();
        callallUser.enqueue(new Callback<List<CusstomerInfo>>() {
            @Override
            public void onResponse(Call<List<CusstomerInfo>> call, Response<List<CusstomerInfo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    loginResponseList.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CusstomerInfo>> call, Throwable t) {

            }
        });


    }

    void otpsend(String phone) {

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(RegiterPhoneActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                Toast.makeText(RegiterPhoneActivity.this, "OTP is successfully send.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegiterPhoneActivity.this, XacthucSDT.class);
                intent.putExtra("phone", phone);
                intent.putExtra("verificationId", s);
                startActivity(intent);

            }

        };
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+84" + phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(RegiterPhoneActivity.this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private boolean check(String phoneNumber) {
        Boolean x = true;
        if (radioButtonxacnhan.isChecked() == false) {
            Toast.makeText(getApplicationContext(), "Bạn Cần Đồng ý với Điều khoản", Toast.LENGTH_SHORT).show();
            return false;

        }
        if (phoneNumber.matches("\\d{10}"))
            return true;
            // validating phone number with -, . or spaces
        else if (phoneNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
            return true;
            // validating phone number with extension length from 3 to 5
        else if (phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
            return true;
            // validating phone number where area code is in braces ()
        else if (phoneNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
            return true;
            // Validation for India numbers
        else if (phoneNumber.matches("\\d{4}[-\\.\\s]\\d{3}[-\\.\\s]\\d{3}"))
            return true;
        else if (phoneNumber.matches("\\(\\d{5}\\)-\\d{3}-\\d{3}"))
            return true;

        else if (phoneNumber.matches("\\(\\d{4}\\)-\\d{3}-\\d{3}"))
            return true;
            // return false if nothing matches the input
        else
            return false;

    }

}