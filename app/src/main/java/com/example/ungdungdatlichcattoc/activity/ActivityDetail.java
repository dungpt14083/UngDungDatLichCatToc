package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_imgNewfeed;
import com.example.ungdungdatlichcattoc.R;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

public class ActivityDetail extends AppCompatActivity {
    ListView lv ;
    TextView title , contentTitle , description  ;
    Adapter_imgNewfeed adapter_imgNewfeed ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = findViewById(R.id.tile_detail_newfeed);
        contentTitle = findViewById(R.id.tile_contentTitle_newfeed);
//        description = findViewById(R.id.title_description_newfeed);

        lv = findViewById(R.id.imgDetail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null)
        {
            title.setText(bundle.getString("title"));
            contentTitle.setText(bundle.getString("contentTitle"));
            description.setText(bundle.getString("description"));
            //description.setText("bùi văn chung ");
            //contentTitle.setText(bundle.getString("contentTitle"));
            String[] a = bundle.getStringArray("img");
            adapter_imgNewfeed = new Adapter_imgNewfeed(getApplicationContext(), Arrays.asList(a));
            lv.setAdapter(adapter_imgNewfeed);
            Log.e("TAG", "link anh: "+ bundle.getString("img") );
        }

    }
}