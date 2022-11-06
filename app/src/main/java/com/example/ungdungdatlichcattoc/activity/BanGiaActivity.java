package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.Service;

import java.util.List;

public class BanGiaActivity extends AppCompatActivity {
    private GridView gridView;
    private List<Service> serviceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_gia);
        gridView =findViewById(R.id.banggia_gridview);
    }
}