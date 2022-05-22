package com.example.quanlythucdon.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {

    Context context;

    public MyDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, Integer version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public  void  executeSQL(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor retrieveData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return  database.rawQuery(sql, null);
    }

    public void CreateItem(Context context, String name, String category, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("category", category);
        values.put("price", Integer.parseInt(price));
        long records;
        records = db.insert("thucdon", null, values);
        if (records > 0) {
            Toast.makeText(context.getApplicationContext(), "Thêm thành công !", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context.getApplicationContext(), "Thêm thất bại !", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public List<String> getAll() {
        List<String> list = new ArrayList<>();
        String selectQuery = "SELECT  * FROM theloai";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
}
