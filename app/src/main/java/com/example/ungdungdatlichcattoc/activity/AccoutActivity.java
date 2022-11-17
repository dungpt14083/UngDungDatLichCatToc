package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ungdungdatlichcattoc.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AccoutActivity extends AppCompatActivity {
  private   TextView tv_account_birthday,tv_account_phone,tv_account_nameuser,tv_account_update,tv_account_adress;
  private   String nameUser,birThday,Phone,token,adress;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accout);
        tv_account_nameuser =findViewById(R.id.tv_account_nameuser);
        tv_account_birthday =findViewById(R.id.tv_account_birthday);
        tv_account_phone =findViewById(R.id.tv_account_phone);
        tv_account_update=findViewById(R.id.tv_account_update);
        tv_account_adress=findViewById(R.id.tv_account_adress);
        tv_account_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(AccoutActivity.this,UpdateAccountActivity.class);
                startActivity(intent);
            }
        });
        getUserinfo();
    }
    void getUserinfo(){
        prefs = getSharedPreferences("HAIR", MODE_PRIVATE);
        nameUser = prefs.getString("nameUser", toString());
        birThday = prefs.getString("birthday",toString());
        Phone =prefs.getString("phone",toString());
        token = prefs.getString("token",toString());
        adress = prefs.getString("address",toString());
        tv_account_adress.setText(adress);
        tv_account_nameuser.setText(nameUser);
        tv_account_phone.setText(Phone);
        tv_account_adress.setText(adress);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(birThday);
            System.out.println(date);
            tv_account_birthday.setText(format.format(date));
        } catch (Exception e) {

        }


    }
}