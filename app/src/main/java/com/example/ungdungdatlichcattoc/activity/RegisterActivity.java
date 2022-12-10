package com.example.ungdungdatlichcattoc.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.Utils;
import com.example.ungdungdatlichcattoc.model.CusstomerInfo;
import com.example.ungdungdatlichcattoc.model.LoginResponse;
import com.example.ungdungdatlichcattoc.model.RegisterResponse;
import com.example.ungdungdatlichcattoc.model.Service;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextPhone, editTextPassword, editTextCnfPassword;
    Button buttonRegister;

    TextView textViewLogin;

    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    List<LoginResponse> loginResponseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextCnfPassword = findViewById(R.id.editTextCnfPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        loginResponseList= new ArrayList<>();
        textViewLogin = findViewById(R.id.textViewLogin);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String phone = editTextPhone.getText().toString().trim();
//                String password = editTextPassword.getText().toString().trim();
//                String passwordConf = editTextCnfPassword.getText().toString().trim();
//
//                List<EditText> list = new ArrayList<>();
//                list.add(editTextPhone);
//                list.add(editTextPassword);
//                list.add(editTextCnfPassword);
//                if (!Utils.checkValidate(list)) {
//                    return;
//                }
//
//                if (password.equals(passwordConf)) {
//                    SharedPreferences.Editor editor = getSharedPreferences("Register", MODE_PRIVATE).edit();
//                    editor.putString("phone", phone);
//                    editor.putString("password", password);
//                    editor.apply();
//                    otpsend(phone);
//                    Intent intent = new Intent(RegisterActivity.this, XacthucSDT.class);
//                    startActivity(intent);
//                    // register(phone, password);
//                } else {
//                    Toast.makeText(RegisterActivity.this, "Password is not matching", Toast.LENGTH_SHORT).show();
//                }
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
                Toast.makeText(RegisterActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                Toast.makeText(RegisterActivity.this, "OTP is successfully send.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, XacthucSDT.class);
                intent.putExtra("phone", phone);
                intent.putExtra("verificationId", s);
                startActivity(intent);

            }

        };
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+84" + phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(RegisterActivity.this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


}
