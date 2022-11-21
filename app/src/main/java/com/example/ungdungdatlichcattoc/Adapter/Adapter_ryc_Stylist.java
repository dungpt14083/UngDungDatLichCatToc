package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.HairStylish;

import java.util.List;


public class Adapter_ryc_Stylist extends RecyclerView.Adapter<Adapter_ryc_Stylist.ViewHolder>{

    private List<HairStylish> listdata;

    // RecyclerView recyclerView;
    public Adapter_ryc_Stylist(List<HairStylish> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.layout_item_stylish_rcv, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HairStylish myListData = listdata.get(position);
        holder.textView.setText(myListData.getNameStylist());
        Glide.with(holder.avatar.getContext()).load("http://io.supermeo.com:8000/" + myListData.getImageStylist()).into(holder.avatar);

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avatar;
        public ImageView select;
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.avatar = (ImageView) itemView.findViewById(R.id.item_stylish_avatar);
            this.select = (ImageView) itemView.findViewById(R.id.btn_select_stylist);
            this.textView = (TextView) itemView.findViewById(R.id.item_stylish_tvname);
        }
    }
}
