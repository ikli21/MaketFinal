package com.example.maket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.maket.Database.Data;
import com.example.maket.Database.Entities.Product;
import com.example.maket.databinding.ActivityAddEditBinding;

public class AddEditActivity extends AppCompatActivity {

    ActivityAddEditBinding binding;
    int idCategory = 0, idProduct = 0;
    Data data;
    Product currentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        idCategory = getIntent().getIntExtra(Data.ID,-1);
        idProduct = getIntent().getIntExtra(Data.ID_PRODUCT,-1);
        data=Data.getInstance(getApplicationContext());
        if(idProduct != -1)
        {
            data.getProductInID(idProduct).observe(AddEditActivity.this, new Observer<Product>() {
                @Override
                public void onChanged(Product productsValue) {
                    currentProduct =productsValue;
                    binding.fullnameET.setText(currentProduct.TitleProduct);
                    binding.shortNameET.setText(String.valueOf(currentProduct.ShortName));
                    binding.descriptionET.setText(currentProduct.Description);
                    binding.URLET.setText(String.valueOf(currentProduct.URLPhotoProduct));
                    binding.priceET.setText(String.valueOf(currentProduct.Price));

                }
            });
            binding.button.setText("Редактировать");
        }
        else currentProduct = new Product();

        binding.URLET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                data.loadImage(binding.URLET.getText().toString(),binding.imageView6);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void AddEditClick(View view) {
        currentProduct.TitleProduct=data.getStringEditText(binding.fullnameET);
        currentProduct.Price=Double.parseDouble(data.getStringEditText(binding.priceET));
        currentProduct.ShortName=data.getStringEditText(binding.shortNameET);

        currentProduct.Description=data.getStringEditText(binding.descriptionET);

        currentProduct.ProductCategoryID=idCategory;
        currentProduct.URLPhotoProduct=data.getStringEditText(binding.URLET);
        if(idProduct!=-1){
            data.db.productDao().update(currentProduct);
        }
        else{
            data.db.productDao().insert(currentProduct);
        }

    }
}