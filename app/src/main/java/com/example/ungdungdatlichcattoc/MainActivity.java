package com.example.ungdungdatlichcattoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.UI.Fragment_DatLich;
import com.example.ungdungdatlichcattoc.UI.Fragment_User;
import com.example.ungdungdatlichcattoc.UI.Fragment_home;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActionBar toolbar;
    private  long Pressed;
    Toast mToas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new Fragment_home()).commit();

        }
        addControl();
        addEvent();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.frag_home:
                            selectedFragment = new Fragment_home();
                            break;

                        case R.id.frag_user:
                            selectedFragment = new Fragment_User();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,selectedFragment).commit();
                    return true;
                }
            };
    private  void addEvent()
    {

    }
    private void addControl(){
        toolbar = getSupportActionBar();
    }

    @Override
    public void onBackPressed() {
        if(Pressed + 2000 >System.currentTimeMillis()){
            mToas.cancel();
            moveTaskToBack(true);
        }else {
            mToas=Toast.makeText(getApplicationContext(),"ấn 2 lần để thoát",Toast.LENGTH_LONG);
            mToas.show();
        }
        Pressed=System.currentTimeMillis();
    }
}