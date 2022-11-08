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
import com.example.ungdungdatlichcattoc.Model.Newfeed;
import com.example.ungdungdatlichcattoc.R;

import java.util.List;

public class NewFeedAdapter extends BaseAdapter {
    private Context context ;
    private List<Newfeed> newFeedList ;

    public NewFeedAdapter(Context context, List<Newfeed> newFeedList) {
        this.context = context;
        this.newFeedList = newFeedList;
    }

    @Override
    public int getCount() {
        return newFeedList.size();
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
        Log.d("asdfgh", "onCreate: "+newFeedList.size());
        ViewHolder viewHolder ;
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
        Newfeed newf = newFeedList.get(i);
        Glide.with(viewHolder.avatar.getContext()).load(newf.getImage()).into(viewHolder.avatar);
        viewHolder.title.setText(newf.getTitle());
        return  view ;
    }
    class ViewHolder{
        TextView title;
        ImageView avatar;
    }
}
