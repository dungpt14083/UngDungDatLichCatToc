package com.example.ungdungdatlichcattoc.activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.ungdungdatlichcattoc.Adapter.SliderPageAdapter;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.UI.Fragment_Page1;
import com.example.ungdungdatlichcattoc.UI.Fragment_Page2;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    private ViewPager viewPager2;
    private SliderPageAdapter pageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        viewPager2=findViewById(R.id.viewPage2_splash);

        List<Fragment> mList=new ArrayList<>();
        mList.add(new Fragment_Page1());
        mList.add(new Fragment_Page2());

        pageAdapter=new SliderPageAdapter(getSupportFragmentManager(),mList);
        viewPager2.setAdapter(pageAdapter);


    }
}