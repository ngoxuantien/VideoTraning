package com.example.mibitelver2.retrofit;

import com.example.mibitelver2.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofit = null;
    public static Retrofit getClient(String baseurl){
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

        Gson gson = new GsonBuilder().setLenient().create(); // hỗ trợ việc conver
        retrofit = new Retrofit.Builder().baseUrl(baseurl)
                .client(builder) // các cài đặt muốn set ở trên
                .addConverterFactory(GsonConverterFactory.create(gson)) // chuyển biến của json về java
                .build();

        return retrofit;
    }
}
