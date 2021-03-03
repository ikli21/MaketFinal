package com.example.maket.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.maket.Dao.CategoryDao;
import com.example.maket.Dao.ProductDao;
import com.example.maket.Database.Entities.Category;
import com.example.maket.Database.Entities.Product;

@Database(entities = {Category.class, Product.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract ProductDao productDao();
    public  abstract CategoryDao categoryDao();
}
