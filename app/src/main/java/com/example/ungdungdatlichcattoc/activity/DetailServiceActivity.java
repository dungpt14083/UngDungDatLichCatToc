package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_text;
import com.example.ungdungdatlichcattoc.R;
import com.google.protobuf.StringValue;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DetailServiceActivity extends AppCompatActivity {
    ImageView Img_detail_service;
    ImageView btn_back_detail_service;
    TextView tv_desc_detail,tv_nameService_detail,tv_price_detail;
    AppCompatButton chondichvu_btn_detailservice;
    String idService,descservice,nameservice;
    int priceservice;
    ArrayList<String> listnamesevice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_service);
        Img_detail_service =findViewById(R.id.Img_detail_service);
        tv_desc_detail = findViewById(R.id.tv_desc_detail);
        btn_back_detail_service =findViewById(R.id.btn_back_detail_service);
        tv_nameService_detail = findViewById(R.id.tv_nameService_detail);
        tv_price_detail=findViewById(R.id.tv_price_detail);
        chondichvu_btn_detailservice =findViewById(R.id.chondichvu_btn_detailservice);
        listnamesevice= new ArrayList<>();
        try {
            getdataService();
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            tv_desc_detail.setText(descservice);
            tv_price_detail.setText(formatter.format(priceservice)+ " VNĐ");
            tv_nameService_detail.setText(nameservice+"");
        }catch (Exception e){
            e.printStackTrace();
        }

        btn_back_detail_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),BangGiaActivity.class);
                startActivity(intent);
                finish();
            }
        });
        chondichvu_btn_detailservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(getApplicationContext(), DatlichActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("sumprice", priceservice);
                    bundle.putStringArray("listidservice", new String[]{idService});
                    bundle.putStringArrayList("listnameservice", listnamesevice);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
    void getdataService() {

        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {

                Glide.with(Img_detail_service.getContext()).load("http://io.supermeo.com:8000/"+bundle.getString("imageDetail")).into(Img_detail_service);
                idService = bundle.getString("idServiceDetail");
                descservice  =bundle.getString("descServiceDetail");
                nameservice =bundle.getString("nameServiceDetail");
                priceservice =bundle.getInt("priceServiceDetail");

                listnamesevice.add(nameservice);
                System.out.println("detail "+nameservice);
                System.out.println("detail "+priceservice);




            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}