package com.example.ungdungdatlichcattoc.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ungdungdatlichcattoc.API.ApiNewFeed;
import com.example.ungdungdatlichcattoc.Adapter.NewFeedAdapter;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.Newfeed;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity_newfeed extends AppCompatActivity {
    GridView gridView ;
    NewFeedAdapter newFeedAdapter ;
    List<Newfeed> newfeedList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_khampha);
        gridView = findViewById(R.id.khampha_grid) ;
        //newfeedList = new
        //this is api
//        newFeedAdapter = new NewFeedAdapter(Activity_newfeed.this , newfeedList) ;
//        gridView.setAdapter(newFeedAdapter) ;
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
                    for (int i = 0; i<newfeedList.size() ; i++)
                    {
                        Log.e("TAG", "onResponse: "+ newfeedList.get(i).image);
                        Newfeed newfeed = newfeedList.get(i);

                    }
                }
            }
            @Override
            public void onFailure(Call<List<Newfeed>> call, Throwable t) {
                Toast.makeText(Activity_newfeed.this, "lỗi ", Toast.LENGTH_SHORT).show();
                Log.e("ktloi", "onFailure: "+t );
            }
        });

    }
}
