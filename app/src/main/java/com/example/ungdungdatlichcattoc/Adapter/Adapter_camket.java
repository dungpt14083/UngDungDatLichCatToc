package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.camket;

import java.util.List;

public class Adapter_camket extends BaseAdapter {
    Context context;
    List<camket> list;
    LayoutInflater inflater;

    public Adapter_camket(Context context, List<camket> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolde2 holder;
        if (view==null){
            view = inflater.inflate(R.layout.custom_item_camket,null);
            holder = new ViewHolde2();
            holder.icon2 = (ImageView) view.findViewById(R.id.img_icon2);
            holder.title_camket = (TextView) view.findViewById(R.id.title_camket);
            holder.content_camket = (TextView) view.findViewById(R.id.content_camket);
            view.setTag(holder);
        }else {
            holder = (ViewHolde2) view.getTag();
        }
        camket camket = this.list.get(i);
        holder.icon2.setImageResource(camket.getIcon());
        holder.title_camket.setText(camket.getDay());
        holder.content_camket.setText(camket.getContent());
        return view;
    }
    static class ViewHolde2{
        ImageView icon2;
        TextView title_camket;
        TextView content_camket;
    }
}
