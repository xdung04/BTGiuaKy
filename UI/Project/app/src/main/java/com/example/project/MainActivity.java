package com.example.project;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.adapters.CategoryAdapter;
import com.example.project.models.CategoryResponse;
import com.example.project.network.APIService;
import com.example.project.models.Category;
import com.example.project.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcCate;
    CategoryAdapter categoryAdapter;
    APIService apiService;
    List<CategoryResponse> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        // Gán LayoutManager và Adapter (với danh sách rỗng) ngay khi Activity khởi tạo
        rcCate.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        categoryAdapter = new CategoryAdapter(this, categoryList);
        rcCate.setAdapter(categoryAdapter);

        // Sau đó, gọi API để lấy dữ liệu
        GetCategory();
    }

    private void AnhXa() {

        rcCate = findViewById(R.id.recyclerCategories);
        rcCate.setHasFixedSize(true);
        rcCate.setLayoutManager(new GridLayoutManager(this, 3));
        rcCate.setAdapter(categoryAdapter);
    }

    public void GetCategory() {
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getCategoryAll().enqueue(new Callback<List<CategoryResponse>>() {
            @Override
            public void onResponse(Call<List<CategoryResponse>> call, Response<List<CategoryResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Cập nhật danh sách mới từ API
                    categoryList = response.body();
                    // Cập nhật dữ liệu cho Adapter
                    categoryAdapter.updateData(categoryList);
                    Log.d("API_RESPONSE", "Data size: " + categoryList.size());
                } else {
                    Log.e("API_ERROR", "Response error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<CategoryResponse>> call, Throwable t) {
                Log.e("API_ERROR", "Failed to load data: " + t.getMessage());
            }
        });
    }
}