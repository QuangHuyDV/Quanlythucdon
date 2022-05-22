package com.example.quanlythucdon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlythucdon.entity.Thucdon;
import com.example.quanlythucdon.model.MyDatabase;
import com.example.quanlythucdon.model.ThucdonAdapter;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    EditText txtNameEdit, txtCategoryEdit, txtPriceEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent itent = getIntent();
        int id = itent.getIntExtra("idE", 0);
        String name = itent.getStringExtra("nameE");
        String category = itent.getStringExtra("categoryE");
        int price = itent.getIntExtra("priceE", 0);
        Capnhat123(id, name, category, price);

    }

    public void Capnhat123(int id, String name, String category, int price) {
        MyDatabase database = new MyDatabase(this, "mydatabase.db", null,1);
        Button btnUpdate = findViewById(R.id.btnEditT);
        Button btnCancel3 = findViewById(R.id.btnCancel3);
        txtNameEdit = (EditText) findViewById(R.id.txtNameEdit);
        txtCategoryEdit = (EditText) findViewById(R.id.txtCategoryEdit);
        txtPriceEdit = (EditText) findViewById(R.id.txtPriceEdit);

        txtNameEdit.setText(name);
        txtCategoryEdit.setText(category);
        txtPriceEdit.setText(String.valueOf(price));

        btnCancel3.setOnClickListener(view -> {
            Intent it = new Intent(UpdateActivity.this, ListActivity.class);
            startActivity(it);
        });

        btnUpdate.setOnClickListener(v -> {
            String name1 = txtNameEdit.getText().toString();
            String category1 = txtCategoryEdit.getText().toString();
            int price1 = Integer.parseInt(txtPriceEdit.getText().toString());
            database.executeSQL("update thucdon set name = '"+name1+"', category = '"+category1+"', price = "+price1+" where id = "+id);
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(UpdateActivity.this, ListActivity.class);
            startActivity(it);
        });

    }

}