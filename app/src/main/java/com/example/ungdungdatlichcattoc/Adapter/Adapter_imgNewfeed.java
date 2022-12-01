package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.Newfeed;

import java.util.List;

public class Adapter_imgNewfeed extends BaseAdapter {
    Context context ;
    private List<String>  listImg ;
    public Adapter_imgNewfeed(Context context, List<String> listImg) {
        this.context = context;
        this.listImg = listImg;
    }

    @Override
    public int getCount() {
        return listImg.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return listImg.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder ;

        if (view== null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.imgnewfeed , null);
            viewHolder.img = view.findViewById(R.id.imgnewfeed) ;
            for (int a =0;a< listImg.size();a++)
            {
                Glide.with(viewHolder.img).load("http://io.supermeo.com:8000/"+listImg.get(i)).into(viewHolder.img);
            }

        }
        return  view;
    }
    class ViewHolder{
        ImageView img;
    }
}
