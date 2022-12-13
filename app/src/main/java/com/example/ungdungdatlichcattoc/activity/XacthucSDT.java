package com.example.ungdungdatlichcattoc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.RegisterResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class XacthucSDT extends AppCompatActivity {
    private String verificationId;
    EditText editText1,editText2,editText3,editText4,editText5,editText6;
    TextView tvphone,btnthulaiveri;
    Button btnsucsses;
    SharedPreferences prefs;
    private int selectPosition = 0;
    ImageView img_veri_back;
    String phone,pass;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;
    private int resnedcode=60;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifi);
        selectPosition=0;
        editText1 = findViewById(R.id.edt_verify_1);
        editText2 =findViewById(R.id.edt_verify_2);
        editText3 =findViewById(R.id.edt_verify_3);
        editText4 = findViewById(R.id.edt_verify_4);
        editText5 =findViewById(R.id.edt_verify_5);
        editText6 =findViewById(R.id.edt_verify_6);
        tvphone =findViewById(R.id.tv_verify_phone);
        btnthulaiveri=findViewById(R.id.btnthulaiveri);
        btnsucsses =findViewById(R.id.btn_verify_tieptuc);
        img_veri_back=findViewById(R.id.img_veri_back);
        //hic
        editText1.addTextChangedListener(textWatcher);
        editText2.addTextChangedListener(textWatcher);
        editText3.addTextChangedListener(textWatcher);
        editText4.addTextChangedListener(textWatcher);
        editText5.addTextChangedListener(textWatcher);
        editText6.addTextChangedListener(textWatcher);
        showKeyBroad(editText1);
        mAuth = FirebaseAuth.getInstance();
        //start count down
        countdowntime();
        btnthulaiveri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(XacthucSDT.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCodeSent(@NonNull String news, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        verificationId = news;
                        countdowntime();
                        Toast.makeText(getApplicationContext(), "OTP Send", Toast.LENGTH_SHORT).show();
                    }

                };
                PhoneAuthOptions options =
                        PhoneAuthOptions.newBuilder(mAuth)
                                .setPhoneNumber("+84" + getIntent().getStringExtra("phone"))       // Phone number to verify
                                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                .setActivity(XacthucSDT.this)                 // Activity (for callback binding)
                                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);
            }
        });
        img_veri_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(XacthucSDT.this,RegiterPhoneActivity.class);
                startActivity(intent);
            }
        });
        verificationId = getIntent().getStringExtra("verificationId");
        tvphone.setText(String.format(
                "+84-%s", getIntent().getStringExtra("phone")
        ));
       // editTextInput();

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

                                            Intent intent = new Intent(XacthucSDT.this, RegiterPassworkActivity.class);
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
    private void showKeyBroad(EditText otpText) {
        otpText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(otpText, InputMethodManager.SHOW_IMPLICIT);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            if (editable.length() > 0) {
                if (selectPosition == 0) {
                    selectPosition = 1;
                    showKeyBroad(editText2);

                } else if (selectPosition == 1) {
                    selectPosition = 2;
                    showKeyBroad(editText3);
                } else if (selectPosition == 2) {
                    selectPosition = 3;
                    showKeyBroad(editText4);
                } else if (selectPosition == 3) {
                    selectPosition = 4;
                    showKeyBroad(editText5);
                } else if (selectPosition == 4) {
                    selectPosition = 5;
                    showKeyBroad(editText6);
                } else if (selectPosition == 5) {
                    selectPosition = 6;
                  //  showKeyBroad(editText6);
                } else {

                }
            }
        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            if (selectPosition == 6) {
                selectPosition = 5;
                showKeyBroad(editText5);
            } else if (selectPosition == 5) {
                selectPosition=4;
                showKeyBroad(editText4);
            }
            else if (selectPosition == 4) {
                selectPosition=3;
                showKeyBroad(editText3);
            }
            else if (selectPosition == 3) {
                selectPosition=2;
                showKeyBroad(editText2);
            }
            else if (selectPosition == 2) {
                selectPosition=1;
                showKeyBroad(editText1);
            }
        } else {
            return super.onKeyUp(keyCode, event);
        }
        return super.onKeyUp(keyCode, event);
    }
    private void countdowntime(){
        btnthulaiveri.setActivated(false);
        btnthulaiveri.setTextColor(Color.parseColor("#99000000"));
        new CountDownTimer(resnedcode*1000,1000){

            @Override
            public void onTick(long l) {
                btnthulaiveri.setText("Resend Code {"+(l/1000)+"}");

            }

            @Override
            public void onFinish() {
                btnthulaiveri.setActivated(true);
                btnthulaiveri.setText("Resend Code");
                btnthulaiveri.setTextColor(getApplicationContext().getResources().getColor(android.R.color.holo_blue_dark));


            }
        }.start();
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

}