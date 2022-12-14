package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.Interface.InterfaceClickSelectTopService;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.Service;
import com.example.ungdungdatlichcattoc.model.TopService;

import java.text.DecimalFormat;
import java.util.List;

public class Adapter_TopService extends BaseAdapter {
    private Context context;
    private int idLayout;
    List<TopService> Topservice ;
    private InterfaceClickSelectTopService interfaceClickSelectTopService;

    public Adapter_TopService(Context context, int idLayout, List<TopService> topservice,InterfaceClickSelectTopService interfaceClickSelectTopService) {
        this.context = context;
        this.idLayout = idLayout;
        Topservice = topservice;
        this.interfaceClickSelectTopService=interfaceClickSelectTopService;
    }

    @Override
    public int getCount() {
        if (Topservice.size() != 0 && !Topservice.isEmpty()) {
            return Topservice.size();
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
        TextView item_chondichvu_tvName = (TextView) view.findViewById(R.id.item_Topservice_tvName);
        TextView item_chondichvu_tvDesc = (TextView) view.findViewById(R.id.item_Topservice_tvDesc);
        TextView item_chondichvu_tvPrice = (TextView) view.findViewById(R.id.item_topservice_tvPrice);
      //  TextView item_chondichvu_tvcount = (TextView) view.findViewById(R.id.item_topservice_tvCount);
        ImageView item_banggia_img = (ImageView) view.findViewById(R.id.item_Topservice_img);
        Button item_topservice_btn=view.findViewById(R.id.btn_topservice_datngay);
        final TopService service = Topservice.get(i);
        if (Topservice != null && !Topservice.isEmpty()) {
            item_chondichvu_tvName.setText(service.getName());
            item_chondichvu_tvDesc.setText(service.getDescribe());
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            String price = formatter.format(service.getPrice());
            item_chondichvu_tvPrice.setText(price + " VNĐ");
            item_topservice_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    interfaceClickSelectTopService.onClickItemTopservice(service);

                }
            });
            // Glide.with(holder.img_avatar.getContext()).load(comics.getImage()).into(holder.img_avatar);
            Glide.with(item_banggia_img.getContext()).load("http://io.supermeo.com:8000/"+service.getImages()).into(item_banggia_img);
        }
        return view;
    }
}
