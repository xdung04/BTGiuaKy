package com.example.project.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/login") // Đường dẫn API (thay đổi nếu khác)
    Call<LoginResponse> login(@Body LoginRequest request);
}
