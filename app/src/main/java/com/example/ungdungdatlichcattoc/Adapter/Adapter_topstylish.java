package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.Service;
import com.example.ungdungdatlichcattoc.model.TopStylish;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_topstylish extends RecyclerView.Adapter<Adapter_topstylish.ViewHolder> {
    private Context context;
    private int idLayout;
    private List<TopStylish> topStylishList;

    public Adapter_topstylish(Context context, List<TopStylish> topStylishList) {
        this.context = context;
        this.topStylishList = topStylishList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
      View view = layoutInflater.inflate(R.layout.item_hotstylish_rcv,parent,false);
      ViewHolder viewHolder = new ViewHolder(view);
      return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TopStylish topStylish = topStylishList.get(position);
        holder.tvname.setText(topStylish.getNameStylist());
        holder.tvdesc.setText(topStylish.getDescription());
        Glide.with(holder.avatar.getContext()).load("http://io.supermeo.com:8000/" + topStylish.getImageStylist()).into(holder.avatar);



    }

    @Override
    public int getItemCount() {
        if (topStylishList.size() != 0 && !topStylishList.isEmpty()) {
            return topStylishList.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avatar;

        public TextView tvname,tvdesc;

        public ViewHolder(View itemView) {
            super(itemView);
            this.avatar = (ImageView) itemView.findViewById(R.id.item_topstylish_imgavatar);
            this.tvname = itemView.findViewById(R.id.item_topstylish_tvName);
            this.tvdesc = (TextView) itemView.findViewById(R.id.item_topstylish_desc);

        }
    }
}
