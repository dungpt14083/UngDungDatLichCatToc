package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.user;

import java.util.List;

public class Adapter_user extends BaseAdapter {

    private List<user> listUser;
    private LayoutInflater inflater;
    private Context context;

    public Adapter_user(Context context,List<user> listUser ) {
        this.context = context;
        this.listUser = listUser;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public Object getItem(int i) {
        return listUser.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = inflater.inflate(R.layout.custom_item_danhsach,null);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) view.findViewById(R.id.icon);
            viewHolder.content = (TextView) view.findViewById(R.id.content);
            viewHolder.icon1 = (ImageView) view.findViewById(R.id.icon1);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        user user = this.listUser.get(i);
        viewHolder.icon.setImageResource(user.getIcon());
        viewHolder.content.setText(user.getContent());
        viewHolder.icon1.setImageResource(user.getIcon1());
        return view;
    }
    static class ViewHolder{
        ImageView icon;
        TextView content;
        ImageView icon1;
    }
}
