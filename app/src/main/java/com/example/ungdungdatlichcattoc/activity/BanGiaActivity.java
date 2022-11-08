package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.ungdungdatlichcattoc.MainActivity;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.Service;

import java.util.List;

public class BanGiaActivity extends AppCompatActivity {
    private GridView gridView;
    private List<Service> serviceList;
ImageView btnhomeback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_gia);
        gridView =findViewById(R.id.banggia_gridview);
        btnhomeback = findViewById(R.id.btnhomeBangGia);
        btnhomeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHOme =new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHOme);
            }
        });
    }
}