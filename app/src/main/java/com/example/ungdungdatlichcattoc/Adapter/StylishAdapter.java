package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.HairStylish;
import com.example.ungdungdatlichcattoc.model.Service;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.util.List;

public class StylishAdapter extends RecyclerView.Adapter<StylishAdapter.ViewHolder> {
    private Context context;
    public List<HairStylish> hairStylishList;

    public StylishAdapter(Context context, List<HairStylish> hairStylishList) {
        this.context = context;
        this.hairStylishList = hairStylishList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_stylish_rcv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HairStylish hairStylishs = hairStylishList.get(position);
        if (hairStylishs == null) {
            return;
        }
        holder.itemstylish_tv_name.setText(hairStylishs.getNameStylist());
        Glide.with(holder.itemstylish_img_avt_stylish.getContext()).load("http://io.supermeo.com:8000/" + hairStylishs.getImageStylist()).into(holder.itemstylish_img_avt_stylish);



    }

    @Override
    public int getItemCount() {
        return hairStylishList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemstylish_tv_name;
        ImageView itemstylish_img_avt_stylish;
        MaterialButton item_stylish_btn_chon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemstylish_tv_name = itemView.findViewById(R.id.itemstylish_tv_name);
            itemstylish_img_avt_stylish = itemView.findViewById(R.id.itemstylish_img_avt_stylish);
            item_stylish_btn_chon = itemView.findViewById(R.id.chondichvu_btn_chondichvu);

        }
    }


}


