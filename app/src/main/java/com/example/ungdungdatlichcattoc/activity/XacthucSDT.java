package com.example.ungdungdatlichcattoc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.RegisterResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class XacthucSDT extends AppCompatActivity {
    private String verificationId;
    EditText editText1,editText2,editText3,editText4,editText5,editText6;
    TextView tvphone;
    Button btnsucsses;
    SharedPreferences prefs;
    String phone,pass;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifi);
        editText1 = findViewById(R.id.edt_verify_1);
        editText2 =findViewById(R.id.edt_verify_2);
        editText3 =findViewById(R.id.edt_verify_3);
        editText4 = findViewById(R.id.edt_verify_4);
        editText5 =findViewById(R.id.edt_verify_5);
        editText6 =findViewById(R.id.edt_verify_6);
        tvphone =findViewById(R.id.tv_verify_phone);
        btnsucsses =findViewById(R.id.btn_verify_tieptuc);
        verificationId = getIntent().getStringExtra("verificationId");
        tvphone.setText(String.format(
                "+84-%s", getIntent().getStringExtra("phone")
        ));
        editTextInput();

        btnsucsses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText1.getText().toString().trim().isEmpty() ||
                        editText2.getText().toString().trim().isEmpty() ||
                        editText3.getText().toString().trim().isEmpty() ||
                        editText4.getText().toString().trim().isEmpty() ||
                        editText5.getText().toString().trim().isEmpty() ||
                        editText6.getText().toString().trim().isEmpty()) {
                    Toast.makeText(XacthucSDT.this, "OTP is not Valid!", Toast.LENGTH_SHORT).show();
                } else {
                    if (verificationId != null) {
                        String code =  editText1.getText().toString().trim() +
                                editText2.getText().toString().trim() +
                                editText3.getText().toString().trim() +
                                editText4.getText().toString().trim() +
                                editText5.getText().toString().trim() +
                                editText6.getText().toString().trim();

                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
                        FirebaseAuth
                                .getInstance()
                                .signInWithCredential(credential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                            Toast.makeText(XacthucSDT.this, "Welcome...", Toast.LENGTH_SHORT).show();
                                            prefs = getSharedPreferences("Register", MODE_PRIVATE);
                                            phone = prefs.getString("phone", toString());
                                            pass = prefs.getString("password",toString());
                                            register(phone,pass);
                                            Intent intent = new Intent(XacthucSDT.this, LoginActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                        } else {

                                            Toast.makeText(XacthucSDT.this, "OTP is not Valid!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            }
        });

    }
    private void editTextInput() {
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               editText6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void register(String phone, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/customer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);
        Call<RegisterResponse> call = api.register(phone, password);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(XacthucSDT.this, getString(R.string.register_successfully), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(XacthucSDT.this, response.message(), Toast.LENGTH_SHORT).show();
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