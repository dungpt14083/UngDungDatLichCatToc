package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.itemmenu;

import java.util.List;

public class Adapter_item_menu extends BaseAdapter {
    Context context;
    List<itemmenu> list;
    LayoutInflater inflater;

    public Adapter_item_menu(Context context, List<itemmenu> list) {
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
        ViewHolder1 holder;
        if (view == null) {
            view = inflater.inflate(R.layout.custom_item_menu, null);
            holder = new ViewHolder1();
            holder.icon1 = (ImageView) view.findViewById(R.id.img_icon1);
            holder.content = (TextView) view.findViewById(R.id.tv_content);
            view.setTag(holder);
        } else {
            holder = (ViewHolder1) view.getTag();
        }
        itemmenu itemmenu = this.list.get(i);
        holder.icon1.setImageResource(itemmenu.getIcon());
        holder.content.setText(itemmenu.getContent());
        return view;
    }

    static class ViewHolder1 {
        ImageView icon1;
        TextView content;
    }
}
