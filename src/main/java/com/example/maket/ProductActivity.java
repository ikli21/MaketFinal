package com.example.maket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.maket.Database.Data;
import com.example.maket.Database.Entities.Product;
import com.example.maket.databinding.ActivityProductBinding;

public class ProductActivity extends AppCompatActivity {
    ActivityProductBinding binding;


    Data data;
    Product currentProduct;
    int idProduct = 0,idCategory=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        idProduct = getIntent().getIntExtra(Data.ID,0);

        data=Data.getInstance(this);
        data.getProductInID(idProduct).observe(ProductActivity.this, new Observer<Product>() {
            @Override
            public void onChanged(Product productsValue) {
                currentProduct=productsValue;
                binding.priceTV.setText(String.valueOf(currentProduct.Price)+"p.");
                binding.nameTV.setText(currentProduct.ShortName);
                binding.fullnameTV.setText(currentProduct.TitleProduct);
                binding.descriptionTV.setText(currentProduct.Description);
                data.loadImage(currentProduct.URLPhotoProduct,binding.productImage);
                idCategory = currentProduct.ProductCategoryID;
            }
        });

    }
    public void GoToEdit(View view){
            Intent intent = new Intent(ProductActivity.this, AddEditActivity.class);
            intent.putExtra(Data.ID_PRODUCT, idProduct);
            intent.putExtra(Data.ID, idCategory);
            startActivity(intent);
    }
}