package com.example.mibitelver2.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mibitelver2.databinding.ActivityLoginBinding;
import com.example.mibitelver2.model.tumodel.UserPost;
import com.example.mibitelver2.model.tumodel.UserResponse;
import com.example.mibitelver2.model.tumodel.Video;
import com.example.mibitelver2.retrofit.APIUtils;
import com.example.mibitelver2.retrofit.DataClient;
import com.example.mibitelver2.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        binding.passwordLogin.setText("test");
        binding.phoneLogin.setText("test");
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Login have token
                UserPost user = new UserPost("test","test");
                DataClient dataClient = APIUtils.getData(Constants.url_login);
                Call<UserResponse> checkLogin = dataClient.userLogin(user);
                checkLogin.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        UserResponse userResponse = response.body();
                        Toast.makeText(LoginActivity.this, userResponse.getToken(), Toast.LENGTH_SHORT).show();
                        if (!TextUtils.isEmpty(userResponse.getToken())){
                            Constants.user = userResponse;
                            editor.putInt("idUser",userResponse.getIdUser());
                            editor.putString("username", userResponse.getUsername());
                            editor.putString("token", userResponse.getToken());
                            editor.commit();

                            int state = sharedPreferences.getInt(String.valueOf(Constants.user.getIdUser()),0);
                            if (state == 0){
                                startActivity(new Intent(LoginActivity.this, InterestActivity.class));
                            }
                            if (state == 1){
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại!!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

//
           }
        });

    }

    public void init(){
        UserResponse user = new UserResponse("","1",2,"");
        Constants.user = user;

        // lưu trữ trạng thái ng dùng đã ấn skip hoặc chọn xong chưa
        sharedPreferences = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        // mode private : chỉ lưu trữ và sử dụng trong phạm vi ứng dụng
        editor = sharedPreferences.edit();
    }

}