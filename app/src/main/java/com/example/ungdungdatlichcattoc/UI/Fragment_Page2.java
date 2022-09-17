package com.example.ungdungdatlichcattoc.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ungdungdatlichcattoc.R;

public class Fragment_Page2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup=(ViewGroup) inflater.inflate(R.layout.activity_page3,container,false);

        
        return viewGroup;
    }
}
