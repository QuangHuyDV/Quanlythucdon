package com.example.quanlythucdon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanlythucdon.model.MyDatabase;

import org.json.JSONArray;

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
        database.executeSQL("CREATE TABLE IF NOT EXISTS thucdon(id integer primary key autoincrement, name VARCHAR(100), category VARCHAR(50), price INTERGER)");
        database.executeSQL("CREATE TABLE IF NOT EXISTS theloai(idCate integer primary key autoincrement, nameCate VARCHAR(50))");
        database.executeSQL("CREATE TABLE IF NOT EXISTS taikhoan(idUser integer primary key autoincrement, nameUser VARCHAR(50), email TEXT not null, password TEXT not null)");

        //tạo tài khoản
//        database.executeSQL("INSERT INTO taikhoan(nameUser, email, password) values('huy','huy@gmail.com','123')");

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


        Button btnReserve = (Button) findViewById(R.id.btnReserve);
        btnReserve.setOnClickListener(view -> {
            Intent it = new Intent(MainActivity.this, ListActivity.class);
            startActivity(it);
        });

        Button btnReadFile = (Button) findViewById(R.id.btnReadFile);
        btnReadFile.setOnClickListener(view -> {
//            readJSON(" https://jsonkeeper.com/b/B04F");
            Intent it = new Intent(MainActivity.this, EmployeeActivity.class);
            startActivity(it);
        });
    }

    private  void readJSON(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response -> Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show());
        requestQueue.add(jsonArrayRequest);
    }
}