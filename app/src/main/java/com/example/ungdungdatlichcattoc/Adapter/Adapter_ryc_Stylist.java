package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.Interface.ItemClickListener;
import com.example.ungdungdatlichcattoc.Interface.ItemClickListenerRcv;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.HairStylish;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class Adapter_ryc_Stylist extends RecyclerView.Adapter<Adapter_ryc_Stylist.ViewHolder>{
    private int selectedItemPosition =-1 ;
    private List<HairStylish> listdata;
    private ItemClickListenerRcv itemClickListener;

    // RecyclerView recyclerView;
    public Adapter_ryc_Stylist(List<HairStylish> listdata,ItemClickListenerRcv listener) {
        this.listdata = listdata;
        this.itemClickListener = listener;
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
        holder.crv_stylish_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItemPosition = holder.getAdapterPosition();
                itemClickListener.onClickItemTime(myListData.get_id(),myListData.getNameStylist());
                holder.select.setImageResource(R.drawable.ic_done_stylish2);
                holder.select.setCircleBackgroundColor(Color.parseColor("#2ECC71"));
                notifyDataSetChanged();
            }
        });
        if(selectedItemPosition == position){
            holder.select.setCircleBackgroundColor(Color.parseColor("#2ECC71"));
            holder.select.setImageResource(R.drawable.ic_done_stylish2);
        }

        else{
            holder.select.setImageResource(R.drawable.tranparentbackground);
            holder.select.setCircleBackgroundColor(Color.parseColor("#00000000"));
        }


    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avatar;
        public CircleImageView select;
        public TextView textView;
        public CardView crv_stylish_order;
        public ViewHolder(View itemView) {
            super(itemView);
            this.avatar = (ImageView) itemView.findViewById(R.id.item_stylish_avatar);
            this.select = (CircleImageView) itemView.findViewById(R.id.btn_select_stylist);
            this.textView = (TextView) itemView.findViewById(R.id.item_stylish_tvname);
            this.crv_stylish_order = (CardView) itemView.findViewById(R.id.crv_stylish_order);
        }
    }
}
