package com.example.quanlythucdon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.quanlythucdon.model.MyDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tạo database
        MyDatabase database = new MyDatabase(this, "mydatabase.db", null,1);

        //xóa dữ liệu trong bảng đã có
//        database.executeSQL("DELETE FROM taikhoan");
//        database.executeSQL("DELETE FROM theloai");
//        database.executeSQL("DELETE FROM thucdon");

        //tạo bảng nếu không tông tại
        database.executeSQL("CREATE TABLE IF NOT EXISTS thucdon(id integer primary key autoincrement, name VARCHAR(100), categoryId INTERGER, price INTERGER)");
        database.executeSQL("CREATE TABLE IF NOT EXISTS theloai(idCate integer primary key autoincrement, nameCate VARCHAR(50))");
        database.executeSQL("CREATE TABLE IF NOT EXISTS taikhoan(idUser integer primary key autoincrement, nameUser VARCHAR(50), email TEXT not null, password TEXT not null)");

        //tạo tài khoản
//        database.executeSQL("INSERT INTO taikhoan(nameUser, email, password) values('huy','huy@gmail.com','123')");

        //tạo thể loại
//        database.executeSQL("insert into theloai(nameCate) values('Món lẩu')");
//        database.executeSQL("insert into theloai(idCate, nameCate) values(null, 'Món tráng miệng')");
//        database.executeSQL("insert into theloai(idCate, nameCate) values(null, 'Món chính')");
//        database.executeSQL("insert into theloai(idCate, nameCate) values(null, 'Món nhúng')");
//        database.executeSQL("insert into theloai(idCate, nameCate) values(null, 'Nước uống')");

        //chuyển trang danh sách món ăn
        Toast.makeText(this, "Đã tạo database + table", Toast.LENGTH_SHORT).show();
        Button btnList = (Button) findViewById(R.id.btnList); 
        btnList.setOnClickListener(view -> {
            Intent it = new Intent(MainActivity.this, ListActivity.class);
            startActivity(it);
        });

        //chuyển trang danh sách thể loại
        Button btnLsCate = (Button) findViewById(R.id.btnLsCate);
        btnLsCate.setOnClickListener(view -> {
            Intent it = new Intent(MainActivity.this, ListCateActivity.class);
            startActivity(it);
        });

        //chuyển trang đặt món(chưa làm)
        Button btnReserve = (Button) findViewById(R.id.btnReserve);
        btnReserve.setOnClickListener(view -> {
            Intent it = new Intent(MainActivity.this, ListActivity.class);
            startActivity(it);
        });
    }
}