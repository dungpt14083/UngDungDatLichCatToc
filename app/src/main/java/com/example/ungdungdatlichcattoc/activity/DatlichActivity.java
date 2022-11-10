package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.ungdungdatlichcattoc.MainActivity;
import com.example.ungdungdatlichcattoc.R;

public class DatlichActivity extends AppCompatActivity {
    ImageView btnHomeBack;
    CardView crvchondichvu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datlich);
        anhxa();
        crvchondichvu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentchondichvu = new Intent(getApplicationContext(), ChonDichVuAcitivty.class);
                startActivity(intentchondichvu);
            }
        });
        btnHomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHome);
            }
        });

    }

    void anhxa() {
        btnHomeBack = findViewById(R.id.btnhomeDatLich);
        crvchondichvu = findViewById(R.id.crv_datlich_chondichvu);
    }
}