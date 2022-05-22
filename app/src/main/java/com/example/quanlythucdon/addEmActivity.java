package com.example.quanlythucdon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanlythucdon.entity.Nhanvien;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class addEmActivity extends AppCompatActivity {
    EditText txtTen, txtDiachi, txtEmail, txtLuong;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_em);

        Button btnThemNV = findViewById(R.id.btnThemEm);
        Button btnCancel2 = findViewById(R.id.btnCancel2);
        txtTen = findViewById(R.id.txtTen);
        txtDiachi = findViewById(R.id.txtDiachi);
        txtEmail = findViewById(R.id.txtEmail);
        txtLuong = findViewById(R.id.txtLuong);

        btnCancel2.setOnClickListener(v -> {
            startActivity(new Intent(addEmActivity.this, EmployeeActivity.class));
        });
        btnThemNV.setOnClickListener(v -> {
            themNV(url);
        });

    }

    private void themNV(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    if (response.trim().equals("success")) {
                        Toast.makeText(addEmActivity.this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(addEmActivity.this, EmployeeActivity.class));
                    } else {
                        Toast.makeText(addEmActivity.this, "Thêm nhân viên thất bại", Toast.LENGTH_SHORT).show();
                    }
                }, error -> {
                    Toast.makeText(addEmActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    Log.d("Error", "Có lỗi: " + error.toString());
                }){
//            @Nullable
//            @Override
//            protected Map<String, String> getParam() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("ten", txtTen.getText().toString().trim());
//                params.put("diachi", txtDiachi.getText().toString().trim());
//                params.put("email", txtEmail.getText().toString().trim());
//                params.put("luong", txtLuong.getText().toString().trim());
//                return params;
//            }
        };
        requestQueue.add(stringRequest);

    }
}