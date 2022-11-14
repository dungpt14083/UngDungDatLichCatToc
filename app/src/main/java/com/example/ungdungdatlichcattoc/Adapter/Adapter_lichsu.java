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
import com.example.ungdungdatlichcattoc.model.History;
import com.example.ungdungdatlichcattoc.model.Newfeed;
import com.example.ungdungdatlichcattoc.model.Order;

import java.util.List;

public class Adapter_lichsu extends BaseAdapter {
    List<Order> orderList ;
    Context context ;

    public Adapter_lichsu(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return orderList.size();
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
        ViewHolderls viewHolderls  ;

        if (view == null)
        {
            viewHolderls = new ViewHolderls() ;
            LayoutInflater inflater = (LayoutInflater)context .getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_lichsucut , null);
            viewHolderls.lsNamestylist = view.findViewById(R.id.tv_lichsu_namestylist);
            viewHolderls.lsNameskinner = view.findViewById(R.id.tv_lichsu_nameskinner);
            viewHolderls.lsNamedichvu = view.findViewById(R.id.tv_lichsu_namedichvu);
            viewHolderls.imglichsu = view.findViewById(R.id.img_lichsu_avatar);
            view.setTag(viewHolderls);
        }
        else{
            viewHolderls = (Adapter_lichsu.ViewHolderls) view.getTag();
        }
        final Order order = orderList.get(i);
        viewHolderls.lsNameskinner.setText(order.getSumPrice());
        viewHolderls.lsNamestylist.setText(order.getStylelistId());
        viewHolderls.lsNamedichvu.setText(order.getCustomerId());

        return  view ;
    }
    class ViewHolderls{
        TextView lsNamestylist, lsNameskinner , lsNamedichvu;
        ImageView imglichsu;
    }
}
