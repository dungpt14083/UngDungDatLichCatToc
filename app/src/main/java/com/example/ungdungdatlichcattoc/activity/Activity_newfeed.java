package com.example.ungdungdatlichcattoc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ungdungdatlichcattoc.API.ApiNewFeed;
import com.example.ungdungdatlichcattoc.Adapter.NewFeedAdapter;
import com.example.ungdungdatlichcattoc.MainActivity;
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
    ImageView btnhomeBangGia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfeed);
        gridView = findViewById(R.id.khampha_grid) ;
        btnhomeBangGia = findViewById(R.id.btnhomeBangGia) ;

        btnhomeBangGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
                Intent intentHOme =new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHOme);
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
                    Log.e("n", "onResponse: "+newfeedList.get(0).image[0]);
                    newFeedAdapter = new NewFeedAdapter(Activity_newfeed.this , newfeedList) ;
                    gridView.setAdapter(newFeedAdapter) ;
//                    for (int i = 0; i<newfeedList.size() ; i++)
//                    {
//                        Log.e("TAG", "onResponse: "+ newfeedList.get(i).image);
//                        Newfeed newfeed = newfeedList.get(i);
//                    }
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getApplicationContext(), ActivityDetail.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("vitri",i);
                            bundle.putString("title",newfeedList.get(i).getTitle());
                            bundle.putString("contentTitle",newfeedList.get(i).getContentTitle());
                            bundle.putString("description",newfeedList.get(i).getDescription());
                            bundle.putStringArray("img",newfeedList.get(i).getImage());
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        }
                    });
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
