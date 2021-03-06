package com.example.mibitelver2.api;

import com.example.mibitelver2.util.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit;

    public static Retrofit getIntance() {
        OkHttpClient builder = new OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS) // cho phép đọc trong bao lâu. Măc định là 3s
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS) // thời gian đợi kết nối
                .retryOnConnectionFailure(true)// khơi động khi gặp lỗi
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest  = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + Constants.user.getToken())
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().client(builder)
                    .baseUrl("http://192.168.1.88:9999/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }
}
