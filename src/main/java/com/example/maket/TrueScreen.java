package com.example.maket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maket.Database.Data;
import com.example.maket.Database.Entities.Category;
import com.example.maket.databinding.ItemCategoryBinding;


import java.util.ArrayList;
import java.util.List;

public class TrueScreen extends AppCompatActivity {
    RecyclerView recyclerView;
    CategoryAdapter adapter;
    List<Category> categories;
    LayoutInflater layoutInflater;
    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_true);
        recyclerView = findViewById(R.id.list_meat);
        layoutInflater=getLayoutInflater();
        categories= new ArrayList<>();
        adapter = new CategoryAdapter();
        recyclerView.setAdapter(adapter);

        data=data.getInstance(this);
        data.getCategoryAll().observe(TrueScreen.this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                if(categories==null||categories.size()==0){

                    Category category = new Category();
                    category.Title = "Говядина";
                    category.URLPhoto = "https://avatanplus.com/files/resources/original/5d83abce7effb16d4a571eb8.png";
                    data.db.categoryDao().insert(category);
                }
                TrueScreen.this.categories = categories;
                adapter.notifyDataSetChanged();
            }
        });

    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemCategoryBinding binding = ItemCategoryBinding.inflate(layoutInflater, parent, false);
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final Category category = categories.get(position);

            holder.binding.titleView.setText(category.Title);
            data.loadImage(category.URLPhoto, holder.binding.iteminc.categoryIV);
            holder.binding.descriptionView.setText(category.Description);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TrueScreen.this, CategoryActivity.class);
                    intent.putExtra(Data.ID, category.CategoryID);
                    intent.putExtra(Data.TITLE, category.Title);
                    startActivity(intent);
                }
            });
            holder.binding.iteminc.colorBackIV.setForeground(new ColorDrawable(getColor(R.color.colorrnd)));
        }
        @Override
        public int getItemCount() {
            return categories.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            ItemCategoryBinding binding;
            public ViewHolder(@NonNull ItemCategoryBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }
}