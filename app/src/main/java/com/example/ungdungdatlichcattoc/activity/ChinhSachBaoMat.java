package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.ungdungdatlichcattoc.R;

public class ChinhSachBaoMat extends AppCompatActivity {
    ImageView img_back_private;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinhsachbaomat);
        img_back_private = findViewById(R.id.img_back_private);
         // ngu thì chết
        img_back_private.setOnClickListener(new View.OnClickListener() {
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