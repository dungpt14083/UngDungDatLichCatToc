package com.example.ungdungdatlichcattoc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ungdungdatlichcattoc.MainActivity;
import com.example.ungdungdatlichcattoc.R;

public class LichSuCutActivity extends AppCompatActivity {
    ImageView btnhomebhack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lichsucut);

        btnhomebhack = findViewById(R.id.btnhomeLichSuCut);
        btnhomebhack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome =new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHome);
            }
        });
    }
}