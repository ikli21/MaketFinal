package com.example.maket;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.maket.Database.Entities.Category;
import com.example.maket.Database.Entities.Product;

import java.util.List;

public class ProductWithCategory {

    @Embedded
    public Category category;

    @Relation(parentColumn = "CategoryID",entityColumn = "CategoryID")
    public List<Product> products;
}
