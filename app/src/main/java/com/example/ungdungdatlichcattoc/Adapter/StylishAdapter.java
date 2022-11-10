package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.HairStylish;

import java.util.List;

public class StylishAdapter extends RecyclerView.Adapter<StylishAdapter.ViewHolder>{
    private List<HairStylish> hairStylishList;
    private Context context;

    public StylishAdapter(List<HairStylish> hairStylishList, Context context) {
        this.hairStylishList = hairStylishList;
        this.context = context;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemstylish,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HairStylish stylish =hairStylishList.get(position);
        holder.tvNameStylish.setText(stylish.getNameStylist());
        Glide.with(holder.imgAvatarStylish.getContext()).load("http://io.supermeo.com:8000/"+stylish.getImageStylist()).into(holder.imgAvatarStylish);

    }

    @Override
    public int getItemCount() {
        if (hairStylishList.size() != 0 && !hairStylishList.isEmpty()) {
            return hairStylishList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgAvatarStylish;
        TextView tvNameStylish;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatarStylish = itemView.findViewById(R.id.itemstylish_img_avt_stylish);
            tvNameStylish = itemView.findViewById(R.id.itemstylish_tv_name);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
