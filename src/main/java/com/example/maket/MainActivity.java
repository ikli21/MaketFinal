package com.example.maket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.maket.Dao.CategoryDao;
import com.example.maket.Database.AppDataBase;
import com.example.maket.Database.Data;
import com.example.maket.Database.Entities.Category;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    CategoryDao categoryDao;
    Data data;
    Category category = new Category();
    List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = Data.getInstance(getApplicationContext());
    }

    public void openTrue(View view) {
        Intent intent = new Intent(MainActivity.this, TrueScreen.class);
//        category.CategoryID=0;


        startActivity(intent);
    }
}