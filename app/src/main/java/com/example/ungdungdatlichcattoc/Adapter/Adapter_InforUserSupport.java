package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.InforUserSupport;

import java.util.List;

public class Adapter_InforUserSupport extends BaseAdapter {
    private List<InforUserSupport> supportList;
    private LayoutInflater mInflater;
    private Context context;

    public Adapter_InforUserSupport(Context context, List<InforUserSupport> supportList) {
        this.supportList = supportList;
        this.context = context;
        this.mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return supportList.size();
    }

    @Override
    public Object getItem(int i) {
        return supportList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolderUserDiscover viewHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.custom_item_inforusersupport, null);
            viewHolder = new ViewHolderUserDiscover();
            viewHolder.icon_Discover = (ImageView) view.findViewById(R.id.icon_Discover);
            viewHolder.content_Discover = (TextView) view.findViewById(R.id.content_Discover);
            viewHolder.icon1_Discover = (ImageView) view.findViewById(R.id.icon1_Discover);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolderUserDiscover) view.getTag();
        }
        InforUserSupport inforUserSupport=this.supportList.get(i);
        viewHolder.icon_Discover.setImageResource(inforUserSupport.getIcon());
        viewHolder.content_Discover.setText(inforUserSupport.getContent());
        viewHolder.icon1_Discover.setImageResource(inforUserSupport.getIcon1());
        return view;
    }

    static class ViewHolderUserDiscover {
        ImageView icon_Discover, icon1_Discover;
        TextView content_Discover;
    }
}
