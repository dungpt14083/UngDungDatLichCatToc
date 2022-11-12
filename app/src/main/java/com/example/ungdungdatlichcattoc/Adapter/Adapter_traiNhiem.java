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
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.util.List;

public class Adapter_traiNhiem extends BaseAdapter {
    private Context context;
    private int idLayout;
    private List<Service> serviceList;

    public Adapter_traiNhiem(Context context, int idLayout, List<Service> serviceList) {
        this.context = context;
        this.idLayout = idLayout;
        this.serviceList = serviceList;
    }

    @Override
    public int getCount() {
        if (serviceList.size() != 0 && !serviceList.isEmpty()) {
            return serviceList.size();
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
        TextView tile = (TextView) view.findViewById(R.id.title_trai_nhiem);
        TextView price = (TextView) view.findViewById(R.id.price_trai_nhiem);
        ImageView img = (ImageView) view.findViewById(R.id.img_trai_nhiem);
        final Service service = serviceList.get(i);
        if (serviceList != null && !serviceList.isEmpty()) {
            tile.setText(service.getNameService());
            price.setText(Integer.toString(service.getPrice())+"đ");
            Log.e("TAG123", "getView: "+service.getPrice() );
            Glide.with(img.getContext()).load("http://io.supermeo.com:8000/"+service.getImages()).into(img);
        }
        return view;
    }
}
