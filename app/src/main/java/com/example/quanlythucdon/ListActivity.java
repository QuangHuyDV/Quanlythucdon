package com.example.quanlythucdon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
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
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //chuyển đến trang tạo mới món ăn
        FloatingActionButton btnCreate = (FloatingActionButton) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(view -> {
            Intent it = new Intent(ListActivity.this, AddActivity.class);
            startActivity(it);
        });

        //lấy danh sách món ăn
        list = new ArrayList<Thucdon>();
        listThucdon = findViewById(R.id.liViewMenu);
        adapter = new ThucdonAdapter(this, R.layout.my_menu, list);
        listThucdon.setAdapter(adapter);
        CreateData();

    }

    //tạo+ lấy danh sách thực đơn
    private void CreateData() {
        MyDatabase database = new MyDatabase(ListActivity.this, "mydatabase.db", null, 1);

//        database.executeSQL("insert into thucdon(name, category, price) values('Lẩu gà', 'Món lẩu', 180000)");
//        database.executeSQL("insert into thucdon(name, category, price) values('Lẩu thái cua', 'Món lẩu', 200000)");
//        database.executeSQL("insert into thucdon(name, category, price) values('Lẩu thái tôm', 'Món lẩu', 200000)");
//        database.executeSQL("insert into thucdon(name, category, price) values('Lẩu bò', 'Món lẩu', 180000)");
//        database.executeSQL("insert into thucdon(name, category, price) values('Lẩu hải sản', 'Món lẩu', 210000)");


        Cursor DanhSachMonAn = database.retrieveData("select * from thucdon");
        list.clear();
        for (int i = 0; i < DanhSachMonAn.getCount(); i++) {
            DanhSachMonAn.moveToPosition(i);
            int id = DanhSachMonAn.getInt(0);
            String name = DanhSachMonAn.getString(1);
            String category = DanhSachMonAn.getString(2);
            int price = DanhSachMonAn.getInt(3);
            list.add(new Thucdon(id, name, category, price));
        }
        adapter.notifyDataSetChanged();
    }

    //tìm kiếm
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}