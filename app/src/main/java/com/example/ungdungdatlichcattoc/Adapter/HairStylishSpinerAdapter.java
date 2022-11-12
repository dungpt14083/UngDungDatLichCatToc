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

public class HairStylishSpinerAdapter extends ArrayAdapter<HairStylish> {

    public HairStylishSpinerAdapter(@NonNull Context context, int resource, @NonNull List<HairStylish> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_stylish_spiner_selected,parent,false);
        TextView tvname = convertView.findViewById(R.id.item_stylish_tvname_spiner_selected);
        //ImageView item_stylish_avatar_spiner_selected = convertView.findViewById(R.id.item_stylish_avatar_spiner_selected);

        HairStylish hairStylish = this.getItem(position);
        if(hairStylish!=null){
            tvname.setText(hairStylish.getNameStylist());
           // Glide.with(item_stylish_avatar_spiner_selected.getContext()).load("http://io.supermeo.com:8000/" + hairStylish.getImageStylist()).into(item_stylish_avatar_spiner_selected);

        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_stylish_spiner,parent,false);
        TextView tvname = convertView.findViewById(R.id.item_stylish_tvname_spiner);
        ImageView imgavatar =convertView.findViewById(R.id.item_stylish_avatar_spiner);
        HairStylish hairStylish = this.getItem(position);
        if(hairStylish!=null){
            tvname.setText(hairStylish.getNameStylist());
            Glide.with(imgavatar.getContext()).load("http://io.supermeo.com:8000/" + hairStylish.getImageStylist()).into(imgavatar);

        }
        return convertView;
    }
}
