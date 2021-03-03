package com.example.maket.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.maket.Database.Entities.Product;

import java.util.List;

@Dao
public interface ProductDao
{

    @Query("Select * from Product,category where CategoryID = :CurrentCategory")
    LiveData<List<Product>> GetAllProductInCategory(int CurrentCategory);
    @Query("Select * from Product where ProductID = :CurrentProduct")
    LiveData<Product> GetProductInID(int CurrentProduct);

    @Insert
    void insert(Product product);

    @Delete
    void delete (Product product);
    @Update
    void update (Product product);


}
