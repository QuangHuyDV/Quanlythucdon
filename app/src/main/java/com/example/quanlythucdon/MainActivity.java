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

        MyDatabase database = new MyDatabase(this, "mydatabase.db", null,1);

        //chuyển trang danh sách món ăn
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

        //chức năng(chưa lm)
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
                response -> Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show());
        requestQueue.add(jsonArrayRequest);
    }
}