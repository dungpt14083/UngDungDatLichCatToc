package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.activity.DatlichActivity;
import com.example.ungdungdatlichcattoc.activity.HairStylishActivity;
import com.example.ungdungdatlichcattoc.model.HairStylish;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class HairStylishAdapter extends BaseAdapter {
    private Context context;
    private int idlayout;
    public List<HairStylish> stylishList;
    public HairStylishAdapter(Context context, int idlayout, List<HairStylish> stylishList) {
        this.context = context;
        this.idlayout = idlayout;
        this.stylishList = stylishList;
    }

    @Override
    public int getCount() {
        if(stylishList.size()!=0&&!stylishList.isEmpty()){
            return stylishList.size();
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
        if(view == null)
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(idlayout,viewGroup,false);

        }
        TextView tvNameStylish = (TextView) view.findViewById(com.example.ungdungdatlichcattoc.R.id.item_stylish_tvname);
        ImageView imgAvatar = (ImageView) view.findViewById(R.id.item_stylish_avatar);
//        MaterialButton btnselect = (MaterialButton) view.findViewById(R.id.item_stylish_btn);
        final HairStylish hairStylish = stylishList.get(i);
        if(hairStylish!=null&&!stylishList.isEmpty()){
            tvNameStylish.setText(hairStylish.getNameStylist());
            Glide.with(imgAvatar.getContext()).load("http://io.supermeo.com:8000/" + hairStylish.getImageStylist()).into(imgAvatar);
//            btnselect.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    HairStylishActivity hairStylishActivity = new HairStylishActivity();
//                    String[] listidservice = hairStylishActivity.listidservice;
//                    int sumprice = hairStylishActivity.sumprice;
//                    Intent intent = new Intent(context, DatlichActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("nameStylish",hairStylish.getNameStylist());
//                    bundle.putString("idstylish",hairStylish.get_id());
//                    bundle.putInt("sumprice",sumprice);
//                    bundle.putStringArray("listidservice",listidservice);
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
//
//                }
//            });

        }
        return view;
    }


}
