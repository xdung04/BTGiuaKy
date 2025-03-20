//  22110276-NguyenVanAn
package com.example.project;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIServiceAN {
    @POST("shopcake/account/register/{username}/{email}/{password}/{gender}")
    Call register(AccountRequest accountRequest);

    @POST("shopcake/account/checkmail/{email}")
    Call<Boolean> checkEmailExist(@Path("email") String email);

    // Tạo và gửi OTP
    @POST("shopcake/otp/generate/{email}")
    Call<Boolean> generateOTP(@Path("email") String email);

    @POST("shopcake/otp/verify/{email}/{otp}")
    Call<Boolean> verifyOTP(@Path("email") String email, @Path("otp") String otp);
}
