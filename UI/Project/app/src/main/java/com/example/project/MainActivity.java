package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.adapters.CategoryAdapter;
import com.example.project.adapters.FoodAdapter;
import com.example.project.models.CategoryResponse;
import com.example.project.models.FoodReponse;
import com.example.project.models.UserResponse;
import com.example.project.network.APIService;
import com.example.project.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcCate, rcLastProduct;
    CategoryAdapter categoryAdapter;
    FoodAdapter foodAdapter;
    APIService apiService;
    List<CategoryResponse> categoryList = new ArrayList<>();
    List<FoodReponse> foodList = new ArrayList<>();

    TextView txtUserName;
    ImageView imgAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        // Gán LayoutManager và Adapter cho Category RecyclerView
        rcCate.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(this, categoryList);
        rcCate.setAdapter(categoryAdapter);

        // Gán LayoutManager và Adapter cho Last Product RecyclerView
        rcLastProduct.setLayoutManager(new GridLayoutManager(this, 2));
        foodAdapter = new FoodAdapter(this, foodList);
        rcLastProduct.setAdapter(foodAdapter);

        // Gọi API
        GetCategory();
        GetLastProduct();
        GetUserInfo();
    }

    private void AnhXa() {
        rcCate = findViewById(R.id.recyclerCategories);
        rcLastProduct = findViewById(R.id.recyclerLastProduct);
    }

    public void GetCategory() {
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getCategoryAll().enqueue(new Callback<List<CategoryResponse>>() {
            @Override
            public void onResponse(Call<List<CategoryResponse>> call, Response<List<CategoryResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoryList = response.body();
                    categoryAdapter.updateData(categoryList);
                    categoryAdapter.notifyDataSetChanged();
                    Log.d("API_RESPONSE", "Categories Loaded: " + categoryList.size());
                } else {
                    Log.e("API_ERROR", "Category Response Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<CategoryResponse>> call, Throwable t) {
                Log.e("API_ERROR", "Failed to load categories: " + t.getMessage());
            }
        });
    }

    public void GetLastProduct() {
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getLastProduct().enqueue(new Callback<List<FoodReponse>>() {
            @Override
            public void onResponse(Call<List<FoodReponse>> call, Response<List<FoodReponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    foodList = response.body();
                    foodAdapter.updateData(foodList);
                    foodAdapter.notifyDataSetChanged();
                    Log.d("API_RESPONSE", "Food Loaded: " + foodList.size());
                } else {
                    Log.e("API_ERROR", "Food Response Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<FoodReponse>> call, Throwable t) {
                Log.e("API_ERROR", "Failed to load food: " + t.getMessage());
            }
        });
    }
    private void GetUserInfo() {
        txtUserName = findViewById(R.id.txtName);
        imgAvatar = findViewById(R.id.avatarImage);
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getUserInfo().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserResponse user = response.body();
                    txtUserName.setText("Hi! " + user.getUsername());

                    // Load avatar bằng Glide
                    Glide.with(MainActivity.this)
                            .load(user.getAvatar())
                            .placeholder(R.drawable.icprofile)
                            .error(R.drawable.icprofile)
                            .into(imgAvatar);
                } else {
                    Log.e("USER_INFO", "Failed to get user data");
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("USER_INFO", "Error: " + t.getMessage());
            }
        });
    }

}
