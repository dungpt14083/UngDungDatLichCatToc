package com.example.ungdungdatlichcattoc.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ungdungdatlichcattoc.API.ApiNewFeed;
import com.example.ungdungdatlichcattoc.Adapter.NewFeedAdapter;
import com.example.ungdungdatlichcattoc.MainActivity;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.Newfeed;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LichSuCutActivity extends AppCompatActivity {
    ImageView btnhomebhack;
    SharedPreferences prefs;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lichsucut);

        btnhomebhack = findViewById(R.id.btnhomeLichSuCut);
        btnhomebhack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome =new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHome);
            }
        });
        //
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/newfeed/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiNewFeed apiInterface = retrofit.create(ApiNewFeed.class);
        Call<List<Newfeed>> call = apiInterface.getNewfeed();
        call.enqueue(new Callback<List<Newfeed>>() {
            @Override
            public void onResponse(Call<List<Newfeed>> call, Response<List<Newfeed>> response) {
                if (response.isSuccessful() && response.body()!= null){
                    newfeedList.addAll(response.body());
                    Log.e("TAG", "onResponse: "+newfeedList.size());
                    newFeedAdapter = new NewFeedAdapter(Activity_newfeed.this , newfeedList) ;
                    gridView.setAdapter(newFeedAdapter) ;
                }
            }
            @Override
            public void onFailure(Call<List<Newfeed>> call, Throwable t) {
                Toast.makeText(Activity_newfeed.this, "lỗi ", Toast.LENGTH_SHORT).show();
                Log.e("ktloi", "onFailure: "+t );
            }
        });
    }
    private String token() {
        prefs =getSharedPreferences("HAIR", Context.MODE_PRIVATE);
        token = prefs.getString("token", toString());
        return token;
}