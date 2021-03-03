package com.example.maket.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.maket.Database.Entities.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("Select * from Category")
    LiveData<List<Category>> getCategoryAll();

    @Insert
    void insert (Category category);

    @Delete
    void delete (Category category);
}
