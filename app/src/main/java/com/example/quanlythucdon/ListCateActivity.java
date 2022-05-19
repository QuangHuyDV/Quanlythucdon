package com.example.quanlythucdon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlythucdon.model.MyDatabase;
import com.example.quanlythucdon.entity.Theloai;
import com.example.quanlythucdon.model.TheloaiAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListCateActivity extends AppCompatActivity {

    ListView listCate;
    ArrayList<Theloai> listTheloai;
    TheloaiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cate);

        MyDatabase database = new MyDatabase(this, "mydatabase.db", null,1);
        listCate = findViewById(R.id.lstCate);
        listTheloai = new ArrayList<>();
        adapter = new TheloaiAdapter(this, R.layout.layout_cate, listTheloai);
        listCate.setAdapter(adapter);

//        database.executeSQL("DELETE FROM theloai");
//        database.executeSQL("insert into theloai(nameCate) values('Món lẩu')");
//        database.executeSQL("insert into theloai(idCate, nameCate) values(null, 'Món tráng miệng')");
//        database.executeSQL("insert into theloai(idCate, nameCate) values(null, 'Món chính')");
//        database.executeSQL("insert into theloai(idCate, nameCate) values(null, 'Món nhúng')");
//        database.executeSQL("insert into theloai(idCate, nameCate) values(null, 'Nước uống')");



        createDatabase1(database);
        FloatingActionButton btnCreateCate = (FloatingActionButton) findViewById(R.id.btnCreateCate);
        btnCreateCate.setOnClickListener(v -> displayDialogThemLoai());

    }

    private void createDatabase1(MyDatabase database) {

        Cursor rs = database.retrieveData("select * from theloai");
        listTheloai.clear();
        while (rs.moveToNext()) {
            int id = rs.getInt(0);
            String name = rs.getString(1);
            Theloai theloai = new Theloai(id, name);
            listTheloai.add(theloai);
        }
        adapter.notifyDataSetChanged();
    }

    private void displayDialogThemLoai() {
        MyDatabase database = new MyDatabase(this, "mydatabase.db", null,1);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_them_loai);

        EditText txtNameCate = dialog.findViewById(R.id.txtNameCate);
        Button btnCateCr = dialog.findViewById(R.id.btnCateCr);
        Button btnCateDt = dialog.findViewById(R.id.btnCateDt);

        btnCateCr.setOnClickListener(v -> {
            String nameCate = txtNameCate.getText().toString();
            if (nameCate.equals("")) {
                Toast.makeText(getApplicationContext(), "Bạn cần nhập tên loại ", Toast.LENGTH_SHORT).show();
                txtNameCate.requestFocus();
                return;
            }

            database.executeSQL("insert into theloai(nameCate) values('"+ nameCate +"')");
            Toast.makeText(getApplicationContext(), "Thêm loại thành công", Toast.LENGTH_SHORT).show();

            dialog.dismiss();
            createDatabase1(database);
        });

        btnCateDt.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    public void  displayDialogCapNhatLoai(int idCate, String nameCate) {
        MyDatabase database = new MyDatabase(this, "mydatabase.db", null,1);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sua_loai);

        EditText txtNameCateUp = dialog.findViewById(R.id.txtNameCateUp);
        Button btnCateUp = dialog.findViewById(R.id.btnCateUp);
        Button btnCateDt2 = dialog.findViewById(R.id.btnCateDt2);

        txtNameCateUp.setText(nameCate);

        btnCateDt2.setOnClickListener(v -> {
            dialog.dismiss();
        });
        btnCateUp.setOnClickListener(v -> {
            String name = txtNameCateUp.getText().toString();
            database.executeSQL("update theloai set nameCate = '"+ name +"' where idCate = " + idCate );
            Toast.makeText(this, "Cập nhật thành công !", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        createDatabase1(database);
        dialog.show();
    }

}