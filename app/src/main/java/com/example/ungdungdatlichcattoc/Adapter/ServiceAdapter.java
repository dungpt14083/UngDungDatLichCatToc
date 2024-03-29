package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.Interface.ItemClickSelectListener;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.activity.ChonDichVuAcitivty;
import com.example.ungdungdatlichcattoc.model.CartService;
import com.example.ungdungdatlichcattoc.model.Service;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServiceAdapter extends BaseAdapter {
    private Context context;
    private int idLayout;
    public List<Service> serviceList;
    public ArrayList<String> idservice = new ArrayList<>();
    public ArrayList<Integer> priceservice = new ArrayList<>();
    public ArrayList<String> listNameSevice = new ArrayList<>();
    public ArrayList<CartService> cartServices = new ArrayList<>();
    private ItemClickSelectListener itemClickSelectListener;
    int count=0;


    public ServiceAdapter(Context context, int idLayout, List<Service> serviceList, ItemClickSelectListener itemClickSelectListener1) {
        this.context = context;
        this.idLayout = idLayout;
        this.serviceList = serviceList;
        this.itemClickSelectListener = itemClickSelectListener1;
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
        TextView item_chondichvu_tvName = (TextView) view.findViewById(R.id.item_chondichvu_tvName);
        TextView item_chondichvu_tvDesc = (TextView) view.findViewById(R.id.item_chondichvu_tvDesc);
        TextView item_chondichvu_tvPrice = (TextView) view.findViewById(R.id.item_chondichvu_tvPrice);
        ImageView item_chondichvu_img = (ImageView) view.findViewById(R.id.item_chondichvu_img);
        MaterialButton item_chondichvu_btn_chon = view.findViewById(R.id.item_chondichvu_btn_chon);
        final Service service = serviceList.get(i);
        final int[] step = {0};
        if (serviceList != null && !serviceList.isEmpty()) {
            item_chondichvu_tvName.setText(service.getNameService());
            item_chondichvu_tvDesc.setText(service.getDescribe());
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            String price = formatter.format(service.getPrice());
            item_chondichvu_tvPrice.setText(price + " VNĐ");
            // Glide.with(holder.img_avatar.getContext()).load(comics.getImage()).into(holder.img_avatar);
            Glide.with(item_chondichvu_img.getContext()).load("http://io.supermeo.com:8000/" + service.getImages()).into(item_chondichvu_img);

            item_chondichvu_btn_chon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (step[0] == 0) {
                        count+=1;
                        itemClickSelectListener.clickselectserviceListener(count);
                        cartServices.add(new CartService(service.get_id(), service.getNameService(), service.getPrice()));
//                        listNameSevice.add(service.getNameService());
//                        idservice.add(service.get_id());
//                        priceservice.add(service.getPrice());

                        item_chondichvu_btn_chon.setText("Đã Chọn");
                        step[0] = 1;
                    } else if (step[0] == 1) {
                        count-=1;
                        itemClickSelectListener.clickselectserviceListener(count);
                        item_chondichvu_btn_chon.setText("Chọn");
                        Iterator<CartService> Cartsv = cartServices.iterator();
                        while (Cartsv.hasNext()) {
                            CartService id = Cartsv.next();
                            if (id.getIdservice().equals(service.get_id())) {
                                Cartsv.remove();
                            }
                        }

                        step[0] = 0;
                    }

                    // item_chondichvu_btn_chon.setEnabled(false);

                }
            });
        }
        return view;
    }
}
