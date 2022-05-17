package com.example.quanlythucdon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.quanlythucdon.model.MyDatabase;
import com.example.quanlythucdon.entity.Thucdon;
import com.example.quanlythucdon.model.ThucdonAdapter;
import com.example.quanlythucdon.AddActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ArrayList<Thucdon> list;
    ListView listThucdon;
    ThucdonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FloatingActionButton btnCreate = (FloatingActionButton) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(view -> {
            Intent it = new Intent(ListActivity.this, AddActivity.class);
            startActivity(it);
        });
        list = new ArrayList<Thucdon>();
        listThucdon = findViewById(R.id.liViewMenu);
        adapter = new ThucdonAdapter(this, R.layout.my_menu, list);
        listThucdon.setAdapter(adapter);
        CreateData();
    }

    private void CreateData() {
        MyDatabase database = new MyDatabase(ListActivity.this, "mydatabase.db", null, 1);
        Cursor DanhSachMonAn = database.retrieveData("select * from thucdon");
        list.clear();
        for (int i = 0; i < DanhSachMonAn.getCount(); i++) {
            DanhSachMonAn.moveToPosition(i);
            int id = DanhSachMonAn.getInt(0);
            String name = DanhSachMonAn.getString(1);
            int categoryId = DanhSachMonAn.getInt(2);
            int price = DanhSachMonAn.getInt(3);
            list.add(new Thucdon(id, name, categoryId, price));
        }
        adapter.notifyDataSetChanged();
    }
}