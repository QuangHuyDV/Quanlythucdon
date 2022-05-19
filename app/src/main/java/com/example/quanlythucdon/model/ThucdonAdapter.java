package com.example.quanlythucdon.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.quanlythucdon.R;
import com.example.quanlythucdon.entity.Thucdon;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ThucdonAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private int layout;
    private ArrayList<Thucdon> list;
    private ArrayList<Thucdon> listOld;

    public ThucdonAdapter(Context context, int layout, ArrayList<Thucdon> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        this.listOld = list;
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    list = listOld;
                } else {
                    List<Thucdon> listadd = new ArrayList<>();
                    for (Thucdon thucdon : listOld) {
                        if (thucdon.getName().toLowerCase().contains(strSearch.toLowerCase())) {
                            listadd.add(thucdon);
                        }
                    }
                    list = (ArrayList<Thucdon>) listadd;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<Thucdon>) results.values;
                notifyDataSetChanged();
            }
        };
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
        holder.itemId.setText(String.valueOf(thucdon.getId()));
        holder.itemName.setText(thucdon.getName());
        holder.itemCate.setText(thucdon.getCategory());
        holder.itemPrice.setText(String.valueOf(thucdon.getPrice()));

        return view;
    }
}
