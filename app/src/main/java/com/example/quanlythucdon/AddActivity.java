package com.example.quanlythucdon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlythucdon.entity.Theloai;
import com.example.quanlythucdon.model.MyDatabase;
import com.example.quanlythucdon.model.TheloaiAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText txtName, txtCategory, txtPrice;
    Button btnInsert, btnCancel;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        MyDatabase database = new MyDatabase(this, "mydatabase.db", null, 1);

        txtName = findViewById(R.id.txtName);
        txtCategory = findViewById(R.id.txtCategory);
        txtPrice = findViewById(R.id.txtPrice);
        btnInsert = findViewById(R.id.btnInsert);
        btnCancel = findViewById(R.id.btnCancel);
//        spinner = findViewById(R.id.cateList);
//        spinner.setOnItemSelectedListener(AddActivity.this);

//        getDataCate(database);
//        AdapterView<?> parent;
//        AdapterView<Adapter> parentx;
//        String label = parentx.getItemAtPosition(position).toString();
//

        btnCancel.setOnClickListener(view -> {
            Intent it = new Intent(AddActivity.this, ListActivity.class);
            startActivity(it);
        });
        btnInsert.setOnClickListener(view -> {
            database.CreateItem(this ,txtName.getText().toString().trim()
                    , String.valueOf(txtCategory.getText())
                    , String.valueOf(txtPrice.getText()));
        });
    }

//    private void getDataCate(MyDatabase database) {
//        List<String> catels = database.getAll();
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,catels);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(dataAdapter);
//    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String label = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "You selected: " + label,
//                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}