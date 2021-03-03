package com.example.maket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maket.Database.Data;
import com.example.maket.Database.Entities.Category;
import com.example.maket.Database.Entities.Product;
import com.example.maket.databinding.ItemProductBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Product> products;
    LayoutInflater layoutInflater;
    Data data;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        TextView title = findViewById(R.id.nameTV);
        recyclerView=findViewById(R.id.recyclerProd);
        layoutInflater=getLayoutInflater();
        products= new ArrayList<>();
        adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);
        id = getIntent().getIntExtra(Data.ID,0);
        title.setText(getIntent().getStringExtra(Data.TITLE));
        data = Data.getInstance(this);
        data.getCurrentCategoryProduct(id).observe(CategoryActivity.this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productsValue) {
                if(productsValue==null||productsValue.size()==0){

                    Product product = new Product();
                    product.TitleProduct = "Бифштекс говядина";
                    product.Price = 2000.00;
                    product.ShortName = "Бифштекс";
                    product.Description="Классический бифштекс, прямиком с альпийских полей";
                    product.URLPhotoProduct = "https://avatanplus.com/files/resources/original/572627d5950691546d0b9a6a.png";
                    data.db.productDao().insert(product);
                }
                products=productsValue;
                adapter.notifyDataSetChanged();
            }
        });
    }
    public void GoToAdd(View view){
        Intent intent = new Intent(CategoryActivity.this, AddEditActivity.class);
//        intent.putExtra(Data.ID,id);
        startActivity(intent);
    }


//    public void AddProduct(View view) {
//        Intent intent = new Intent(CategoryActivity.this, AddEditProductActivity.class);
//        intent.putExtra(Data.ID,id);
//        startActivity(intent);
//    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemProductBinding binding = ItemProductBinding.inflate(layoutInflater,parent,false);
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final Product product = products.get(position);

            holder.binding.productnameTV.setText(product.ShortName);
            holder.binding.productpriceTV.setText(String.valueOf(product.Price));
            data.loadImage(product.URLPhotoProduct,holder.binding.productIV);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CategoryActivity.this, ProductActivity.class);
                    intent.putExtra(Data.ID,product.ProductID);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return products.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ItemProductBinding binding;
            public ViewHolder(@NonNull ItemProductBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }
}