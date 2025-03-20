package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText username, email, password, confirm, textOtp;
    RadioButton male, female;
    ImageButton btnRegister;
    Button sendOTP;
    AccountRequest accountRequest = new AccountRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        anhxa();

        sendOTP.setOnClickListener(view -> {
            String emailStr = email.getText().toString();
            if (emailStr.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Vui lòng nhập email!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isValidEmail(emailStr)) {
                Toast.makeText(RegisterActivity.this, "Email không đúng định dạng!", Toast.LENGTH_SHORT).show();
                return;
            }
            accountRequest.setEmail(emailStr);
            sendOTPfunc(emailStr);
        });
        TextView tvLogin = findViewById(R.id.textViewLogin);
        tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });


        btnRegister.setOnClickListener(v -> {
            if (password.getText().toString().equals(confirm.getText().toString())) {
                if (!checkInputText(username.getText().toString(), email.getText().toString(), password.getText().toString(), textOtp.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isStrongPassword(password.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Password phải chứa ít nhất 8 kí tự", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (male.isChecked()) {
                    accountRequest.setGender("male");
                } else if (female.isChecked()) {
                    accountRequest.setGender("female");
                }

                accountRequest.setUsername(username.getText().toString());
                accountRequest.setEmail(email.getText().toString());
                accountRequest.setPassword(password.getText().toString());
                register(accountRequest);
            } else {
                Toast.makeText(RegisterActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void anhxa() {
        textOtp = findViewById(R.id.textOtp);
        sendOTP = findViewById(R.id.sendOTP);
        username = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        confirm = findViewById(R.id.editTextComfirmPassword);
        btnRegister = findViewById(R.id.imageButtonRegister);
        male = findViewById(R.id.radioMale);
        female = findViewById(R.id.radioFemale);
    }

    public boolean checkInputText(String a, String b, String c, String d) {
        return !(a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty());
    }

    public boolean isStrongPassword(String password) {
        return password.length() >= 8;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void register(AccountRequest accountRequest) {
        String OTP = textOtp.getText().toString();
        APIServiceAN apiService = RetrofitAn.getRetrofit().create(APIServiceAN.class);
        Call<Boolean> callVerifyOTP = apiService.verifyOTP(accountRequest.getEmail(), OTP);
        callVerifyOTP.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body()) {
                        Call<Boolean> callCreateAccount = apiService.register(accountRequest);
                        callCreateAccount.enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                if (response.isSuccessful() && response.body() != null && response.body()) {
                                    Toast.makeText(RegisterActivity.this, "Đăng ký tài khoản thành công!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Không thể đăng ký tài khoản, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Log.e("Error", t.getMessage());
                            }
                        });
                    } else {
                        Toast.makeText(RegisterActivity.this, "OTP không đúng hoặc đã hết hạn!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Lỗi khi xác thực OTP!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    private void sendOTPfunc(String emailStr) {
        APIServiceAN apiService = RetrofitAn.getRetrofit().create(APIServiceAN.class);
        Call<Boolean> callCheckEmail = apiService.checkEmailExist(emailStr);
        callCheckEmail.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    boolean isExistingEmail = response.body() != null && response.body();
                    if (!isExistingEmail) {
                        Call<Boolean> callGenerateOTP = apiService.generateOTP(emailStr);
                        callGenerateOTP.enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                if (response.isSuccessful() && response.body() != null && response.body()) {
                                    Toast.makeText(RegisterActivity.this, "Gửi OTP thành công!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Không thể gửi OTP, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Log.e("Error", t.getMessage());
                            }
                        });
                    } else {
                        Toast.makeText(RegisterActivity.this, "Email này đã đăng ký rồi, vui lòng chọn email khác!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Lỗi khi kiểm tra email!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
}
