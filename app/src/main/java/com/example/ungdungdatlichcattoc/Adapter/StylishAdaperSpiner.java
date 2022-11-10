package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.HairStylish;

import java.util.List;

public class StylishAdaperSpiner extends ArrayAdapter<HairStylish> {
    public StylishAdaperSpiner(@NonNull Context context, @NonNull List<HairStylish> stylishList) {
        super(context,  0,stylishList);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    @NonNull

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }
    public View customView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView ==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemstylish,parent,false);

        }
        HairStylish hairStylish = getItem(position);
        ImageView imgavatarhairstylish = convertView.findViewById(R.id.itemstylish_img_avt_stylish);
        TextView tvNameStylish = convertView.findViewById(R.id.itemstylish_tv_name);
        if(hairStylish != null)
        {
            Glide.with(imgavatarhairstylish.getContext()).load("http://io.supermeo.com:8000/"+hairStylish.getImageStylist()).into(imgavatarhairstylish);
            tvNameStylish.setText(hairStylish.getNameStylist());

        }

        return convertView;
    }
}
