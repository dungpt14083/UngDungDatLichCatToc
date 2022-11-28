package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.activity.ActivityDetail;
import com.example.ungdungdatlichcattoc.model.Newfeed;

import java.util.List;

public class NewFeedAdapter extends BaseAdapter {
    private Context context ;
    private List<Newfeed> newFeedList ;
    private String[] listimg;

    public NewFeedAdapter(Context context, List<Newfeed> newFeedList) {
        this.context = context;
        this.newFeedList = newFeedList;
    }
    @Override
    public int getCount() {
        if (newFeedList.size() != 0 && !newFeedList.isEmpty()) {
            return newFeedList.size();
        }
        return 0;

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return newFeedList.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder ;
            final Newfeed newfeed = newFeedList.get(i);
        if (view == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_grid_khampha , null);
            viewHolder.avatar = view.findViewById(R.id.item_khampha_img);
            viewHolder.title = view.findViewById(R.id.item_khampha_tvtitle);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        try{
            listimg = newfeed.getImage();;
            Glide.with(viewHolder.avatar).load("http://io.supermeo.com:8000/"+listimg[0]).into(viewHolder.avatar);
        }catch (Exception e){
            e.printStackTrace();
        }


        viewHolder.title.setText(newfeed.getTitle());
        return  view ;
    }
    class ViewHolder{
        TextView title;
        ImageView avatar;
    }
}
