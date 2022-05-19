package com.example.quanlythucdon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanlythucdon.entity.Nhanvien;
import com.example.quanlythucdon.model.NhanvienAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EmployeeActivity extends AppCompatActivity {
    ListView lstNhanVien;
    ArrayList<Nhanvien> listEm;
    NhanvienAdapter EmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        lstNhanVien = findViewById(R.id.lstEmployee);
        listEm = new ArrayList<>();

        getListJSON("https://jsonkeeper.com/b/B04F");

        EmAdapter = new NhanvienAdapter(this, R.layout.employee_details, listEm);
        lstNhanVien.setAdapter(EmAdapter);
    }

    private void getListJSON(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            Nhanvien nv = new Nhanvien(object.getInt("id"), object.getString("name"),
                                    object.getString("address"), object.getString("email"), object.getInt("salary"));
                            listEm.add(nv);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        EmAdapter.notifyDataSetChanged();
                    }
                }, error -> Toast.makeText(EmployeeActivity.this, error.toString(), Toast.LENGTH_SHORT).show());
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnAddEm) {
            startActivity(new Intent(EmployeeActivity.this, addEmActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.employee_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}