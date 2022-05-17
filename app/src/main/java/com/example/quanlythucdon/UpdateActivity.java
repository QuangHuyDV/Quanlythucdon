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

public class UpdateActivity extends AppCompatActivity {
    TextView lblId;
    EditText lblName, lblImg, lblCate, lblPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        final Button btnHome = (Button) findViewById(R.id.btnHome);
        lblId = (TextView) findViewById(R.id.lblId);
        lblImg = (EditText) findViewById(R.id.lblImg);
        lblName = (EditText) findViewById(R.id.lblName);
        lblCate = (EditText) findViewById(R.id.lblCategory);
        lblPrice = (EditText) findViewById(R.id.lblPrice);

        btnUpdate.setOnClickListener((View.OnClickListener) (view) -> {
            SQLiteDatabase database = openOrCreateDatabase("thucdon", MODE_PRIVATE, null);
            String name = lblName.getText().toString();
            String img = lblImg.getText().toString();
            String cate = lblCate.getText().toString();
            int price = Integer.parseInt(lblPrice.getText().toString());

            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("category", cate);
            values.put("price", price);
            values.put("image", img);

            long records = database.update("book", values, "id = ?",
                    new String[]{lblId.getText().toString()});
            if(records > 0) {
                Toast.makeText(getApplicationContext(), "Cập nhật thành công",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Cập nhật thất bại",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(UpdateActivity.this, ListActivity.class);
                startActivity(it);
            }
        });

        displayDetails();
    }

    private void displayDetails() {
//        TextView lblId = (TextView) findViewById(R.id.lblId);
//        EditText lblName = (EditText) findViewById(R.id.lblName);
//        EditText lblImg = (EditText) findViewById(R.id.lblImg);
//        EditText lblCate = (EditText) findViewById(R.id.lblCategory);
//        EditText lblPrice = (EditText) findViewById(R.id.lblPrice);

        Intent it = getIntent();

        String id = it.getStringExtra("id");

        SQLiteDatabase database = openOrCreateDatabase("mydatabase", MODE_PRIVATE, null);

        Cursor rs = database.query("thucdon", new String[] {"id", "name", "img", "category", "price"}, "id = ?",
                new String[] {String.valueOf(id)}, null, null, null, null);

        if (rs != null) {
            rs.moveToFirst();
        }
        String Id = rs.getString(0);
        String name = rs.getString(1);
        String img = rs.getString(2);
        String cate = rs.getString(3);
        int price = rs.getInt(4);

        lblName.setText(Id + "");
        lblImg.setText(name + "");
        lblImg.setText(img + "");
        lblCate.setText(cate + "");
        lblPrice.setText(price + "");
    }
}