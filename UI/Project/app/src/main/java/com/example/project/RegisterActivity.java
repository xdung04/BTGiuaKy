package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText username, email, password, confirm, textOtp;
    RadioButton male, female;
    ImageButton btnRegister;

//    Button sendOTP;
    AccountRequest accountRequest = new AccountRequest();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        anhxa();

//        sendOTP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                accountRequest.setEmail(email.getText().toString());
//                   sendOTPfunc();
//            }
//        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    
                if (password.getText().toString().equals(confirm.getText().toString()))
                {
                    accountRequest.setUsername(username.getText().toString());
                    accountRequest.setEmail(email.getText().toString());
                    accountRequest.setPassword(password.getText().toString());
                    register(accountRequest);
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Mat khau khong trung khop", Toast.LENGTH_SHORT).show();
                }

            }
        });
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountRequest.setGender("male");
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountRequest.setGender("female");
            }
        });
    }


    void anhxa(){
//        textOtp = findViewById(R.id.textOtp);
//        sendOTP = findViewById(R.id.sendOTP);
        username = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        confirm = findViewById(R.id.editTextComfirmPassword);
        btnRegister = findViewById(R.id.imageButtonRegister);
        male = findViewById(R.id.radioMale);
        female = findViewById(R.id.radioFemale);
    }

    public boolean checkPasswordEqual(String a, String b){
        if (a.equals(b))
            return true;
        else

            return false;
    }

    // Kiểm tra đầu vào khong trống
    public boolean checkInputText (String a, String b, String c, String d)
    {
        if (a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty())
            return false;
        else
            return true;
    }

    // Kiểm tra độ mạnh password
    public boolean isStrongPassword(String password){
        // Kiểm tra độ dài mật khẩu
        if (password.length() < 8) {
            return false;
        }

        // Kiểm tra có ít nhất một chữ cái viết hoa
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // Kiểm tra có ít nhất một chữ cái viết thường
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        // Kiểm tra có ít nhất một chữ số
        if (!password.matches(".*\\d.*")) {
            return false;
        }
        return true;
    }

    // Hàm kiểm tra email hợp lệ
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        // Khởi tạo đối tượng Pattern và Matcher để kiểm tra email
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        // Trả về true nếu email hợp lệ, false nếu không hợp lệ
        return matcher.matches();
    }

    private void register(AccountRequest accountRequest){
        String emailstr = email.getText().toString();
        String pwd= password.getText().toString();
        String confirmPwd= confirm.getText().toString();
        String OTP = textOtp.getText().toString();
        if (!checkInputText(emailstr, pwd,confirmPwd, OTP))
        {
            Toast.makeText(RegisterActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isStrongPassword(pwd)){
            Toast.makeText(RegisterActivity.this, "Password phải chứ ít nhất 8 kí tự, 1 chữ hoa, 1 chữ thường, 1 số!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!checkPasswordEqual(pwd, confirmPwd)){
            Toast.makeText(RegisterActivity.this, "Password không trùng khớp!", Toast.LENGTH_SHORT).show();
            return;
        }

        APIServiceAN apiService = RetrofitAn.getRetrofit().create(APIServiceAN.class);
        Call<Boolean> callVerifyOTP = apiService.verifyOTP(emailstr, OTP);
        callVerifyOTP.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()){
                    if (response.body())
                    {
                        Call<Boolean> callCreateAccount = apiService.register(accountRequest);
                        callCreateAccount.enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                if (response.isSuccessful()){
                                    if (response.body())
                                    {
                                        Toast.makeText(RegisterActivity.this, "Đăng ký tài khoản thành công!", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(RegisterActivity.this, "Không thể đăng ký tài khoản, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(RegisterActivity.this, "Lỗi hệ thống khi đăng ký tài khoản", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Log.e("Error",t.getMessage());
                            }
                        });

                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "OTP không đúng hoặc đã hết hạn!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Lỗi khi xác thực OTP !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });

    }

    private void sendOTPfunc(){
        String emailstr = email.getText().toString();
        if (emailstr.isEmpty())
        {
            Toast.makeText(RegisterActivity.this, "Vui lòng nhập email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isValidEmail(emailstr))
        {
            Toast.makeText(RegisterActivity.this, "Email không đúng định dạng!", Toast.LENGTH_SHORT).show();
            return;
        }
        APIServiceAN apiService = RetrofitAn.getRetrofit().create(APIServiceAN.class);
        Call<Boolean> callCheckEmail = apiService.checkEmailExist(emailstr);
        callCheckEmail.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response)
            {
                if (response.isSuccessful()){
                    boolean isExistingEmail = response.body();
                    if (!isExistingEmail){
                        Call<Boolean> callGenerateOTP = apiService.generateOTP(emailstr);
                        callGenerateOTP.enqueue(new Callback<Boolean>(){

                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                if (response.isSuccessful()) {
                                    Boolean isOTPSent = response.body();
                                    if (isOTPSent) {
                                        Toast.makeText(RegisterActivity.this, "Gửi OTP thành công!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "OTP không trùng khớp hoặc đã hết hạn!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(RegisterActivity.this, "Lỗi không gửi được OTP!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Log.e("Error", t.getMessage());
                            }
                        } );
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Email này đã đăng ký rồi, vui lòng chọn email khác!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Log.e("DEBUG", "Response failed: " + response.code() + " - " + response.message());
                    Toast.makeText(RegisterActivity.this, "Lỗi không kiểm tra được email trên hệ thống!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("DEBUG", "API Error: " + t.getMessage());
            }
        });
    }
}

