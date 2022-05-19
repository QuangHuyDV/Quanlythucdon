package com.example.quanlythucdon.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlythucdon.R;
import com.example.quanlythucdon.entity.Nhanvien;

import java.util.ArrayList;

public class NhanvienAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Nhanvien> nhanvienList;

    public NhanvienAdapter(Context context, int layout, ArrayList<Nhanvien> nhanvienList) {
        this.context = context;
        this.layout = layout;
        this.nhanvienList = nhanvienList;
    }

    @Override
    public int getCount() {
        return nhanvienList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtEmName, txtEmAddress, txtEmEmail, txtEmSalary;
        ImageView imgEmEdit, imgEmDelete;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.txtEmName = view.findViewById(R.id.txtEmName);
            holder.txtEmAddress = view.findViewById(R.id.txtEmAddress);
            holder.txtEmEmail = view.findViewById(R.id.txtEmEmail);
            holder.txtEmSalary = view.findViewById(R.id.txtEmSalary);
            holder.imgEmEdit = view.findViewById(R.id.imgEmEdit);
            holder.imgEmDelete = view.findViewById(R.id.imgEmDelete);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Nhanvien nhanvien = nhanvienList.get(i);

        holder.txtEmName.setText("Tên :"+nhanvien.getTen());
        holder.txtEmAddress.setText("Địa chỉ:"+nhanvien.getDiachi());
        holder.txtEmEmail.setText("Email:"+nhanvien.getEmail());
        holder.txtEmSalary.setText(String.valueOf("Lương: "+nhanvien.getLuong()));


        return view;
    }
}
