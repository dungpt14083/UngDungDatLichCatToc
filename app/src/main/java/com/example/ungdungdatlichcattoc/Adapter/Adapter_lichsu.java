package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.History;
import com.example.ungdungdatlichcattoc.model.Newfeed;

import java.util.List;

public class Adapter_lichsu extends BaseAdapter {
    List<History> historyList ;
    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       NewFeedAdapter.ViewHolder viewHolder ;
        final History history = historyList.get(i);
        if (view == null)
        {
            viewHolder = new NewFeedAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_grid_khampha , null);
            viewHolder.avatar = view.findViewById(R.id.item_khampha_img);
            viewHolder.title = view.findViewById(R.id.item_khampha_tvtitle);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (NewFeedAdapter.ViewHolder) view.getTag();
        }
        Newfeed newf = newFeedList.get(i);
        Glide.with(viewHolder.avatar).load("http://io.supermeo.com:8000/"+newfeed.getImage()[0]).into(viewHolder.avatar);
        viewHolder.title.setText(newf.getTitle());
        return  view ;
    }
}
