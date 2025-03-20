//22110346 Trần Tuấn Kha
package com.example.project.network;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.example.project.models.Category;
import com.example.project.models.CategoryResponse;
import com.example.project.models.FoodReponse;
import com.example.project.models.UserResponse;

public interface APIService {
    @GET("/api/category/")
    Call<List<CategoryResponse>> getCategoryAll();

    @GET("api/account/{email}")
    Call<UserResponse> getUserInfo(@Path("email") String email);

    @GET("api/lastproduct")
    Call<List<FoodReponse>> getLastProduct();
}