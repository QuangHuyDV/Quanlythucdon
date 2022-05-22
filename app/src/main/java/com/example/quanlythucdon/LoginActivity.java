package com.example.quanlythucdon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlythucdon.model.MyDatabase;

public class LoginActivity extends AppCompatActivity {
    EditText txtEmailTest, txtPasswordTest;
    MyDatabase database;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmailTest = findViewById(R.id.txtEmailTest);
        txtPasswordTest = findViewById(R.id.txtPasswordTest);
        btnLogin = findViewById(R.id.btnLogin);
        database = new MyDatabase(this,"mydatabase.db", null, 1);
        //tạo bảng nếu không tông tại
        database.executeSQL("CREATE TABLE IF NOT EXISTS thucdon(id integer primary key autoincrement, name VARCHAR(100), category VARCHAR(50), price INTERGER)");
        database.executeSQL("CREATE TABLE IF NOT EXISTS theloai(idCate integer primary key autoincrement, nameCate VARCHAR(50))");
        database.executeSQL("CREATE TABLE IF NOT EXISTS taikhoan(idUser integer primary key autoincrement, nameUser VARCHAR(50), email TEXT not null, password TEXT not null)");

        //xóa dữ liệu trong bảng đã có
//        database.executeSQL("DELETE FROM taikhoan");
//        database.executeSQL("DELETE FROM theloai");
//        database.executeSQL("DELETE FROM thucdon");

        // Tạo loại món
//        database.executeSQL("insert into theloai(nameCate) values('Món lẩu')");
//        database.executeSQL("insert into theloai(idCate, nameCate) values(null, 'Món tráng miệng')");
//        database.executeSQL("insert into theloai(idCate, nameCate) values(null, 'Món chính')");
//        database.executeSQL("insert into theloai(idCate, nameCate) values(null, 'Món nhúng')");
//        database.executeSQL("insert into theloai(idCate, nameCate) values(null, 'Nước uống')");

        //Tạo món ăn
//        database.executeSQL("insert into thucdon(name, category, price) values('Lẩu gà', 'Món lẩu', 180000)");
//        database.executeSQL("insert into thucdon(name, category, price) values('Lẩu thái cua', 'Món lẩu', 200000)");
//        database.executeSQL("insert into thucdon(name, category, price) values('Lẩu thái tôm', 'Món lẩu', 200000)");
//        database.executeSQL("insert into thucdon(name, category, price) values('Lẩu bò', 'Món lẩu', 180000)");
//        database.executeSQL("insert into thucdon(name, category, price) values('Lẩu hải sản', 'Món lẩu', 210000)");
//        database.executeSQL("insert into thucdon(name, category, price) values('Mực khô', 'Món khai vị', 20000)");
//        database.executeSQL("insert into thucdon(name, category, price) values('Coca', 'Nước uống', 20000)");



        //tạo tài khoản
//        database.executeSQL("INSERT INTO taikhoan(nameUser, email, password) values('huy','huy@gmail.com','123')");
//        Toast.makeText(this, "Đã tạo database + table", Toast.LENGTH_SHORT).show();

        btnLogin.setOnClickListener(v -> {
            String email = txtEmailTest.getText().toString();
            String password = txtPasswordTest.getText().toString();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this, "Bạn cần nhập đầy đủ dữ liệu đăng nhập !", Toast.LENGTH_SHORT).show();
            } else {
                if (!checkEmail(email, database)) {
                    Toast.makeText(LoginActivity.this, "Bạn nhập sai email !!!", Toast.LENGTH_SHORT).show();
                } else if (!checkPassword(password, database)) {
                    Toast.makeText(LoginActivity.this, "Bạn nhập sai mật khẩu !!!", Toast.LENGTH_SHORT).show();
                } else if (checkEmail(email, database) && checkPassword(password, database)) {
                    Intent it = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(it);
                }
            }
        });
    }

    public Boolean checkEmail(String email, MyDatabase database) {
        Cursor cursor = database.retrieveData("select * from taikhoan where email = '"+ email + "'");
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkPassword(String password, MyDatabase database) {
        Cursor cursor = database.retrieveData("select * from taikhoan where password = '"+ password + "'");
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}