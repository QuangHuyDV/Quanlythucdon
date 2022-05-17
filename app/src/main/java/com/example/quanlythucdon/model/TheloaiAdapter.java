package com.example.quanlythucdon.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlythucdon.R;
import com.example.quanlythucdon.entity.Theloai;

import java.util.List;

public class TheloaiAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Theloai> listTheloai;

    public TheloaiAdapter(Context context, int layout, List<Theloai> listTheloai) {
        this.context = context;
        this.layout = layout;
        this.listTheloai = listTheloai;
    }

    @Override
    public int getCount() {
        return this.listTheloai.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder {
        TextView lblCateId, lblNameCate;
        ImageView imgDeleCate, imgEditCate;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;

        if(view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.lblCateId = (TextView) view.findViewById(R.id.lblCateId);
            holder.lblNameCate = (TextView) view.findViewById(R.id.lblNameCate);
            holder.imgEditCate = view.findViewById(R.id.imgEditCate);
            holder.imgDeleCate = view.findViewById(R.id.imgDeleCate);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Theloai theloai = listTheloai.get(i);
        holder.lblCateId.setText(theloai.getIdCate());
        holder.lblNameCate.setText(theloai.getNameCate());

        return view;
    }
}
