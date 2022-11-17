package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.API.ApiCustomer;
import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.CusstomerInfo;
import com.example.ungdungdatlichcattoc.model.RegisterResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateAccountActivity extends AppCompatActivity {

    TextInputEditText edt_update_account_username, edt_update_account_birthday, edt_update_account_Adress;
    MaterialButton btn_updateaccount_Update;
    private String nameUser, birThday, Phone, token, andress;
    SharedPreferences prefs;
    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        edt_update_account_username = findViewById(R.id.edt_update_account_username);
        edt_update_account_birthday = findViewById(R.id.edt_update_account_birthday);
        edt_update_account_Adress = findViewById(R.id.edt_update_account_Adress);
        btn_updateaccount_Update = findViewById(R.id.btn_updateaccount_Update);

        getUserinfo();
        edt_update_account_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(edt_update_account_birthday);
            }
        });
        btn_updateaccount_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date1 = format.parse(edt_update_account_birthday.getText().toString());
                    System.out.println(date1);
                    Update(token, edt_update_account_username.getText().toString(), date1, edt_update_account_Adress.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }

    void getUserinfo() {
        prefs = getSharedPreferences("HAIR", MODE_PRIVATE);
        nameUser = prefs.getString("nameUser", toString());
        birThday = prefs.getString("birthday", toString());
        Phone = prefs.getString("phone", toString());
        token = prefs.getString("token", token);
        andress = prefs.getString("address", toString());
        edt_update_account_username.setText(nameUser);
        edt_update_account_Adress.setText(andress);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(birThday);
            System.out.println(date);
            edt_update_account_birthday.setText(format.format(date));
        } catch (Exception e) {

        }

    }

    private void showDateTimeDialog(final TextInputEditText date_time_in) {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new DatePickerDialog(UpdateAccountActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void Update(String token, String edt_update_account_username, Date edt_update_account_birthday, String edt_update_account_Adress) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/customer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiCustomer api = retrofit.create(ApiCustomer.class);
        Call<CusstomerInfo> call = api.UpdateCustomer(token, edt_update_account_username, edt_update_account_Adress, edt_update_account_birthday);
        call.enqueue(new Callback<CusstomerInfo>() {
            @Override
            public void onResponse(Call<CusstomerInfo> call, Response<CusstomerInfo> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Dã Thay đổi thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateAccountActivity.this, AccoutActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<CusstomerInfo> call, Throwable t) {

            }
        });
    }
}