package com.example.maket.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    public int CategoryID;

    public String URLPhoto;

    public String Title;
    public String Description;
}
