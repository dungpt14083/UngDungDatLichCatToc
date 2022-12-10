package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.Interface.InterfaceHistory;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.History;
import com.example.ungdungdatlichcattoc.model.Newfeed;
import com.example.ungdungdatlichcattoc.model.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Adapter_lichsu extends BaseAdapter {
    List<Order> orderList ;
    Context context ;
    private InterfaceHistory interfaceHistory;
    public Adapter_lichsu(List<Order> orderList, Context context,InterfaceHistory interfaceHistory) {
        this.orderList = orderList;
        this.context = context;
        this.interfaceHistory =interfaceHistory;
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
            viewHolderls.lsTime = view.findViewById(R.id.tv_lichsu_thoigian) ;
            viewHolderls.lsdiachi = view.findViewById(R.id.tv_lichsu_diachi) ;
            viewHolderls.btn_datlai =view.findViewById(R.id.btn_history_datlai);
            //viewHolderls.imglichsu = view.findViewById(R.id.img_lichsu_avatar);
            view.setTag(viewHolderls);
        }
        else{
            viewHolderls = (ViewHolderls) view.getTag();
        }
        final Order order = orderList.get(i);
        viewHolderls.lsNameskinner.setText(order.getSumPrice());
        viewHolderls.lsNamestylist.setText(order.getNameStylist());
        viewHolderls.lsdiachi.setText("Số 7 Thiền Quang");
        viewHolderls.lsNameskinner.setText(order.getNameStylist());
        String a [] = orderList.get(i).getNameServices();
        viewHolderls.btn_datlai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceHistory.onClickItemHistory(order);
            }
        });
        Log.e("TAG3", "getView:"+a );
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(order.getTime());
            System.out.println(date);
            viewHolderls.lsTime.setText(format.format(date));
        } catch (Exception e) {

        }
        //(order.getTime());
        viewHolderls.lsNamedichvu.setText("");
        for(int y=0; y< a.length; y++){
            viewHolderls.lsNamedichvu.append(a[y]+"\n");   //prints all strings in the array
            //viewHolderls.lsNamedichvu.append("\n");
        }
        return  view ;
    }
    class ViewHolderls{
        TextView lsNamestylist, lsNameskinner , lsNamedichvu , lsTime , lsdiachi;
        ImageView imglichsu;
        Button btn_datlai;
    }
}
