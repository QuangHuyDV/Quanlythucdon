package com.example.quanlythucdon.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlythucdon.R;
import com.example.quanlythucdon.entity.Thucdon;

import java.util.ArrayList;

public class ThucdonAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Thucdon> list;

    public ThucdonAdapter(Context context, int layout, ArrayList<Thucdon> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView itemId, itemName, itemCate, itemPrice;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.itemId = view.findViewById(R.id.itemId);
            holder.itemName = view.findViewById(R.id.itemName);
            holder.itemCate = view.findViewById(R.id.itemCate);
            holder.itemPrice = view.findViewById(R.id.itemPrice);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        Thucdon thucdon = list.get(i);
        holder.itemId.setId(thucdon.getId());
        holder.itemName.setText(thucdon.getName());
        holder.itemCate.setId(thucdon.getCategoryId());
        holder.itemPrice.setId(thucdon.getPrice());

        return view;
    }
}
