package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ungdungdatlichcattoc.Adapter.Adapter_imgNewfeed;
import com.example.ungdungdatlichcattoc.Adapter.SliderAdapter;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.PhotoDes;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class ActivityDetail extends AppCompatActivity {
    ListView lv ;
    TextView title , contentTitle , description  ;
    Adapter_imgNewfeed adapter_imgNewfeed ;
    ImageView img_back_detail;
    ViewPager2 viewpage2_discover;
    private CircleIndicator3 circleIndicator3;
    List<PhotoDes> photoDesList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_discover);
        title = findViewById(R.id.tile_detail_discover_newfeed);
        contentTitle = findViewById(R.id.tile_contentTitle_discover_newfeed);
        description = findViewById(R.id.title_description_newfeed);
        img_back_detail= findViewById(R.id.img_back_dk);
        viewpage2_discover =findViewById(R.id.viewpage2_discover);
        circleIndicator3 =findViewById(R.id.cicler_indicator3);
        photoDesList= new ArrayList<>();

        img_back_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(ActivityDetail.this,Activity_newfeed.class);
                    startActivity(intent);
                    finishAndRemoveTask();
                }catch (Exception e)
                {

                }


            }
        });
     //   lv = findViewById(R.id.imgDetail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null)
        {
            try {
                title.setText(bundle.getString("title"));
                contentTitle.setText(bundle.getString("contentTitle"));
                description.setText(bundle.getString("description"));
                //description.setText("bùi văn chung ");
                //contentTitle.setText(bundle.getString("contentTitle"));
                String[] a = bundle.getStringArray("img");
                for( int i=0;i<=a.length;i++){
                    photoDesList.add(new PhotoDes(a[i]));

                }

             //   adapter_imgNewfeed = new Adapter_imgNewfeed(getApplicationContext(), Arrays.asList(a));
            //    lv.setAdapter(adapter_imgNewfeed);
                Log.e("TAG", "link anh: "+ bundle.getString("img") );
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        SliderAdapter sliderAdapter = new SliderAdapter(this,photoDesList);
        viewpage2_discover.setAdapter(sliderAdapter);
        circleIndicator3.setViewPager(viewpage2_discover);

    }
}