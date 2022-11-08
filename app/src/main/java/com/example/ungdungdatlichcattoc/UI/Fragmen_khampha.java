package com.example.ungdungdatlichcattoc.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ungdungdatlichcattoc.Adapter.NewFeedAdapter;
import com.example.ungdungdatlichcattoc.Api.ApiNewFeed;
import com.example.ungdungdatlichcattoc.Model.Newfeed;
import com.example.ungdungdatlichcattoc.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragmen_khampha extends Fragment {
    GridLayout gridLayout ;
    NewFeedAdapter newFeedAdapter ;
    List<Newfeed> newfeedList ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_khampha,container,false);
        gridLayout = view.findViewById(R.id.khampha_grid) ;

        //this is api
        newFeedAdapter = new NewFeedAdapter(getContext() , newfeedList) ;
         Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/newfeed/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiNewFeed apiInterface = retrofit.create(ApiNewFeed.class);
        Call<List<Newfeed>> call = apiInterface.getNewfeed();
        call.enqueue(new Callback<List<Newfeed>>() {
            @Override
            public void onResponse(Call<List<Newfeed>> call, Response<List<Newfeed>> response) {
                if (response.isSuccessful() && response.body() != null){
                    newfeedList.addAll(response.body());
                    newFeedAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Newfeed>> call, Throwable t) {
                //Toast.makeText(Danhsach.this, "Loi api"+t, Toast.LENGTH_SHORT).show();
                Log.e("ktloi", "onFailure: "+t );
            }
        });
        return view ;

    }
}
