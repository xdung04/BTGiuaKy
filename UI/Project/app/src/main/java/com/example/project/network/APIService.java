//22110346 Trần Tuấn Kha
package com.example.project.network;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import com.example.project.models.Category;

public interface APIService {
    @GET("categories.php")
    Call<List<Category>> getCategoryAll();
}