package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.Interface.InterfaceClickServiceDetail;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.Service;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.util.List;

public class Adapter_BangGia extends BaseAdapter {
    private Context context;
    private int idLayout;
    List<Service> serviceList ;
    InterfaceClickServiceDetail interfaceClickServiceDetail;

    public Adapter_BangGia(Context context, int idLayout, List<Service> serviceList,InterfaceClickServiceDetail interfaceClickServiceDetail) {
        this.context = context;
        this.idLayout = idLayout;
        this.serviceList = serviceList;
        this.interfaceClickServiceDetail =interfaceClickServiceDetail;
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
        TextView item_chondichvu_tvName = (TextView) view.findViewById(R.id.item_banggia_tvName);
        TextView item_chondichvu_tvDesc = (TextView) view.findViewById(R.id.item_banggia_tvDesc);
        TextView item_chondichvu_tvPrice = (TextView) view.findViewById(R.id.item_banggia_tvPrice);
        ImageView item_banggia_img = (ImageView) view.findViewById(R.id.item_banggia_img);
        final Service service = serviceList.get(i);
        if (serviceList != null && !serviceList.isEmpty()) {
            item_chondichvu_tvName.setText(service.getNameService());
            item_chondichvu_tvDesc.setText(service.getDescribe());
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            String price = formatter.format(service.getPrice());
            item_chondichvu_tvPrice.setText(price + " VNĐ");

            // Glide.with(holder.img_avatar.getContext()).load(comics.getImage()).into(holder.img_avatar);
            Glide.with(item_banggia_img.getContext()).load("http://io.supermeo.com:8000/"+service.getImages()).into(item_banggia_img);
            item_banggia_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    interfaceClickServiceDetail.clickService(service);
                }
            });
        }
        return view;
    }
}
