package com.example.quanlythucdon;

import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;

public interface DialogCate {
    boolean onCreateCateMenu(Menu menu);

    boolean onOptionsCate(@NonNull MenuItem item);
}
