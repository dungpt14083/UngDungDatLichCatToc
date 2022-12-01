package com.example.ungdungdatlichcattoc.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.PhotoDes;

public class SliderFragment extends Fragment {
    ImageView imgphotofragphoto;
    private View mview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       mview = inflater.inflate(R.layout.layout_slider_photo,container,false);

       Bundle bundle = getArguments();
        PhotoDes photoDes = (PhotoDes) bundle.get("objPhoto");
        imgphotofragphoto = mview.findViewById(R.id.imgphotofragphoto);
        Glide.with(imgphotofragphoto.getContext()).load("http://io.supermeo.com:8000/"+photoDes.getResouceid()).into(imgphotofragphoto);
        return mview;
    }
}
