package com.example.quanlythucdon.model;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.quanlythucdon.ListActivity;
import com.example.quanlythucdon.R;
import com.example.quanlythucdon.UpdateActivity;
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
        TextView itemId;
        TextView itemName;
        TextView itemCate;
        TextView itemPrice;
        Button btnEdit, btnDele;
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
            holder.btnEdit = view.findViewById(R.id.btnEdit);
            holder.btnDele = view.findViewById(R.id.btnDele);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        Thucdon thucdon = list.get(i);
        holder.itemId.setText(String.valueOf(thucdon.getId()));
        holder.itemName.setText(thucdon.getName());
        holder.itemCate.setText(thucdon.getCategory());
        holder.itemPrice.setText(String.valueOf(thucdon.getPrice()));

        holder.btnEdit.setOnClickListener(v -> {
            UpdateActivity.Capnhat(thucdon.getId(),thucdon.getName(),thucdon.getCategory(),thucdon.getPrice());
        });

        holder.btnDele.setOnClickListener(view1 -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("Xác nhận");
            dialog.setMessage("Bạn có thực sự muốn xóa ?");

            dialog.setPositiveButton("Đồng ý", (dialog1, which) -> deleteThucdon(thucdon.getId()));
            dialog.setNegativeButton("Không", (dialog12, which) -> {
                return;
            });
            dialog.show();
        });
        return view;
    }

    private void deleteThucdon(int id) {
        MyDatabase database = new MyDatabase(context, "mydatabase.db", null, 1);
        database.executeSQL("delete from thucdon where id ="+ id);
        Cursor DanhSachMonAn = database.retrieveData("select * from thucdon");
        list.clear();
        for (int i = 0; i < DanhSachMonAn.getCount(); i++) {
            DanhSachMonAn.moveToPosition(i);
            int id1 = DanhSachMonAn.getInt(0);
            String name = DanhSachMonAn.getString(1);
            String category = DanhSachMonAn.getString(2);
            int price = DanhSachMonAn.getInt(3);
            list.add(new Thucdon(id1, name, category, price));
        }
        notifyDataSetChanged();
    }
}
