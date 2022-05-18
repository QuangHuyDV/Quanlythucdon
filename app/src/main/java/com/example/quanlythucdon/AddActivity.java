package com.example.quanlythucdon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.quanlythucdon.model.MyDatabase;

public class AddActivity extends AppCompatActivity {

    EditText txtName, txtCategory, txtPrice;
    Button btnInsert, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        txtName = findViewById(R.id.txtName);
        txtCategory = findViewById(R.id.txtCategory);
        txtPrice = findViewById(R.id.txtPrice);
        btnInsert = findViewById(R.id.btnInsert);
        btnCancel = findViewById(R.id.btnCancel);
//        btnClear = findViewById(R.id.btnClear);
//        val categorys = getResources().getStringArray(R.array.category);
//        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.category_item);
        btnCancel.setOnClickListener(view -> {
            Intent it = new Intent(AddActivity.this, ListActivity.class);
            startActivity(it);
        });

//        btnClear.setOnClickListener(view -> {
//            txtName.setText("");
//            txtImg.setText("");
//            txtCategory.setText("");
//            txtPrice.setText("");
//        });

        btnInsert.setOnClickListener(view -> {
            MyDatabase database = new MyDatabase(AddActivity.this, "mydatabase.db",null,1);
            database.CreateItem(this ,txtName.getText().toString().trim()
                    , String.valueOf(txtCategory.getText())
                    , txtPrice.getId());
        });
    }

}