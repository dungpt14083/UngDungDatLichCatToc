package com.example.ungdungdatlichcattoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.text;

import java.util.List;

public class Adapter_text extends BaseAdapter {
    Context context;
    List<text> listText;
    LayoutInflater inflater;

    public Adapter_text(Context context, List<text> listText) {
        this.context = context;
        this.listText = listText;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listText.size();
    }

    @Override
    public Object getItem(int i) {
        return listText.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderText holderText;
        if (view==null){
            view = inflater.inflate(R.layout.custom_text_dichvu,null);
            holderText = new ViewHolderText();
            holderText.tv_text = (TextView) view.findViewById(R.id.tv_text);
            view.setTag(holderText);
        }else {
            holderText=(ViewHolderText) view.getTag();
        }
        text text = this.listText.get(i);
        holderText.tv_text.setText(text.getText());
        return view;
    }
    static class ViewHolderText{
        TextView tv_text;
    }
}
