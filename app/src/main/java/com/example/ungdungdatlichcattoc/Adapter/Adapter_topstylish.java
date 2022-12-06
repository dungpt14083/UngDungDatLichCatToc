package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.Service;
import com.example.ungdungdatlichcattoc.model.TopStylish;

import java.util.List;

public class Adapter_topstylish extends BaseAdapter {
    private Context context;
    private int idLayout;
    private List<TopStylish> topStylishList;

    public Adapter_topstylish(Context context, int idLayout, List<TopStylish> topStylishes) {
        this.context = context;
        this.idLayout = idLayout;
        this.topStylishList = topStylishes;
    }

    @Override
    public int getCount() {
        if (topStylishList.size() != 0 && !topStylishList.isEmpty()) {
            return topStylishList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(idLayout, viewGroup, false);
        }
        TextView tile = (TextView) view.findViewById(R.id.title_nameStylist);
        TextView description = (TextView) view.findViewById(R.id.item_stylishtop_description);
        TextView count = (TextView) view.findViewById(R.id.item_stylishtop_count);
        ImageView img = (ImageView) view.findViewById(R.id.img_stylishtop);
        final TopStylish service = topStylishList.get(i);
        if (topStylishList != null && !topStylishList.isEmpty()) {
            tile.setText(service.getNameStylist());
            description.setText(service.getDescription());
            count.setText("Số Lượt cắt: "+service.getCount());
            Glide.with(img.getContext()).load("http://io.supermeo.com:8000/"+service.getImageStylist()).into(img);
        }
        return view;
    }
}
