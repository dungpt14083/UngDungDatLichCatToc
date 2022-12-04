package com.example.ungdungdatlichcattoc.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ungdungdatlichcattoc.R;

public class Activity_DieuKienGiaoDichChung extends AppCompatActivity {

    ImageView img_back_dk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dieukiengiaodichchung);
        img_back_dk =findViewById(R.id.img_back_dk);
        img_back_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}