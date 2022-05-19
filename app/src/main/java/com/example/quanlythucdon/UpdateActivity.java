package com.example.quanlythucdon;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.quanlythucdon.model.MyDatabase;

public class UpdateActivity extends AppCompatActivity {
    EditText txtNameEdit, txtCategoryEdit, txtPriceEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

    }

    public void Capnhat(int id, String name, String category, int price) {
        MyDatabase database = new MyDatabase(this, "mydatabase.db", null,1);
        Button btnUpdate = (Button) findViewById(R.id.btnEditT);
        Button btnHome = (Button) findViewById(R.id.btnCancel2);
        txtNameEdit = (EditText) findViewById(R.id.txtNameEdit);
        txtCategoryEdit = (EditText) findViewById(R.id.txtCategoryEdit);
        txtPriceEdit = (EditText) findViewById(R.id.txtPriceEdit);

        txtNameEdit.setText(name);
        txtCategoryEdit.setText(category);
        txtPriceEdit.setText(price);

        btnHome.setOnClickListener(view -> {
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