package com.example.quanlythucdon;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.quanlythucdon.model.MyDatabase;
import com.example.quanlythucdon.entity.Theloai;
import com.example.quanlythucdon.model.TheloaiAdapter;

import java.util.ArrayList;

public class ListCateActivity extends AppCompatActivity {

    ListView listCate;
    ArrayList<Theloai> listTheloai;
    TheloaiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cate);



        listCate = findViewById(R.id.lstCate);
        listTheloai = new ArrayList<>();
        adapter = new TheloaiAdapter(this, R.layout.layout_cate, listTheloai);
        listCate.setAdapter(adapter);

        createDatabase1();
    }

    private void createDatabase1() {
        MyDatabase database = new MyDatabase(this, "mydatabase.db", null,1);

        database.executeSQL("CREATE TABLE IF NOT EXISTS theloai(idCate INTEGER  PRIMARY KEY AUTOINCREMENT, nameCate VARCHAR(50))");

        database.executeSQL("DELETE FROM theloai");
        database.executeSQL("insert into theloai(nameCate) values('Món lẩu')");
        database.executeSQL("insert into theloai(idCate, nameCate) values(null, 'Món tráng miệng')");
        database.executeSQL("insert into theloai(idCate, nameCate) values(null, 'Món chính')");
        database.executeSQL("insert into theloai(idCate, nameCate) values(null, 'Món nhúng')");
        database.executeSQL("insert into theloai(idCate, nameCate) values(null, 'Nước uống')");

        Cursor rs = database.retrieveData("select * from theloai");
        while (rs.moveToNext()) {
            int id = rs.getInt(0);
            String name = rs.getString(1);

            Theloai theloai = new Theloai(id, name);
            listTheloai.add(theloai);
        }
        adapter.notifyDataSetChanged();
    }
}