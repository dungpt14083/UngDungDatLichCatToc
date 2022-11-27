package com.example.ungdungdatlichcattoc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

public class XacThucSdtQuenMatKhauActivity extends AppCompatActivity {
    private String verificationId;
    EditText editText1,editText2,editText3,editText4,editText5,editText6;
    TextView tvphone;
    Button btnsucsses;
    ImageView img_fogot_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_thuc_sdt_quen_mat_khau);
        editText1 = findViewById(R.id.edt_fogot_verify_1);
        editText2 =findViewById(R.id.edt_fogot_verify_2);
        editText3 =findViewById(R.id.edt_fogot_verify_3);
        editText4 = findViewById(R.id.edt_fogot_verify_4);
        editText5 =findViewById(R.id.edt_fogot_verify_5);
        editText6 =findViewById(R.id.edt_fogot_verify_6);
        tvphone =findViewById(R.id.tv_fogot_verify_phone);
        img_fogot_back =findViewById(R.id.img_fogot_back);
        img_fogot_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(XacThucSdtQuenMatKhauActivity.this,QuenMatKhauActivity.class);
                startActivity(intent);
            }
        });
        btnsucsses =findViewById(R.id.btn_fogot_verify_tieptuc);
        verificationId = getIntent().getStringExtra("verificationId");
        tvphone.setText(String.format(
                "+84-%s", getIntent().getStringExtra("phone")
        ));
        editTextInput();
        btnsucsses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText1.getText().toString().trim().isEmpty() ||
                        editText2.getText().toString().trim().isEmpty() ||
                        editText3.getText().toString().trim().isEmpty() ||
                        editText4.getText().toString().trim().isEmpty() ||
                        editText5.getText().toString().trim().isEmpty() ||
                        editText6.getText().toString().trim().isEmpty()) {
                    Toast.makeText(XacThucSdtQuenMatKhauActivity.this, "OTP is not Valid!", Toast.LENGTH_SHORT).show();
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

                                            Toast.makeText(XacThucSdtQuenMatKhauActivity.this, "Welcome...", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(XacThucSdtQuenMatKhauActivity.this, DoiMatKhauQuenMatKhauActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(XacThucSdtQuenMatKhauActivity.this, "OTP is not Valid!", Toast.LENGTH_SHORT).show();
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
}