package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText username, email, password, confirm;
    RadioButton male, female;
    ImageButton btnRegister;
    AccountRequest accountRequest = new AccountRequest();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        anhxa();

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
        username = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        confirm = findViewById(R.id.editTextComfirmPassword);
        btnRegister = findViewById(R.id.imageButtonRegister);
        male = findViewById(R.id.radioMale);
        female = findViewById(R.id.radioFemale);
    }
    private void register(AccountRequest accountRequest){
        APIServiceAN apiRegister = RetrofitAn.getRetrofit().create(APIServiceAN.class);
        apiRegister.register(accountRequest).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Dang ky khong thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}