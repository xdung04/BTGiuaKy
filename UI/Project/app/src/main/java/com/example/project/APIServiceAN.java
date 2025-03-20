//  22110276-NguyenVanAn
package com.example.project;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIServiceAN {
    // Đăng ký tài khoản (RequestBody)
    @POST("shopcake/account/register")
    Call<Boolean> register(@Body AccountRequest accountRequest);

    // Kiểm tra email tồn tại (GET cho khớp)
    @GET("shopcake/account/checkmail/{email}")
    Call<Boolean> checkEmailExist(@Path("email") String email);

    // Tạo và gửi OTP
    @POST("shopcake/otp/generate/{email}")
    Call<Boolean> generateOTP(@Path("email") String email);

    // Xác thực OTP
    @POST("shopcake/otp/verifyOTP/{email}/{otp}")
    Call<Boolean> verifyOTP(@Path("email") String email, @Path("otp") String otp);
}
