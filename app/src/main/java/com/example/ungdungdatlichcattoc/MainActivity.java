package com.example.ungdungdatlichcattoc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ungdungdatlichcattoc.UI.Fragment_User;
import com.example.ungdungdatlichcattoc.UI.Fragment_home;
import com.example.ungdungdatlichcattoc.activity.DatlichActivity;
import com.example.ungdungdatlichcattoc.activity.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    ActionBar toolbar;
    private long Pressed;
    Toast mToas;
    FloatingActionButton fab_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab_order = findViewById(R.id.fab_order);
        SharedPreferences prefs = getSharedPreferences("HAIR", MODE_PRIVATE);
        String phone = prefs.getString("phone", "");
        String password = prefs.getString("password", "");
        fab_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DatlichActivity.class);
                startActivity(intent);
            }
        });
        if (phone.equals("") && password.equals("")) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Fragment_home()).commit();

        }

        addControl();
        addEvent();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.frag_home:
                            selectedFragment = new Fragment_home();
                            break;

                        case R.id.frag_user:
                            selectedFragment = new Fragment_User();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
                    return true;
                }
            };

    //this is add envent;
    private void addEvent() {

    }

    private void addControl() {
        toolbar = getSupportActionBar();
    }

    @Override
    public void onBackPressed() {
        if (Pressed + 2000 > System.currentTimeMillis()) {
            mToas.cancel();
            moveTaskToBack(true);
        } else {
            mToas = Toast.makeText(getApplicationContext(), "ấn 2 lần để thoát", Toast.LENGTH_LONG);
            mToas.show();
        }
        Pressed = System.currentTimeMillis();
    }
}