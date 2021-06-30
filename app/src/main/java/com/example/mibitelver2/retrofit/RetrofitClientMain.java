package com.example.mibitelver2.retrofit;

import com.example.mibitelver2.util.Constants;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientMain {
    private static final String BASE_URL = "http://192.168.1.88:9999/api/v1/";
    public static Retrofit retrofit;

    public static Retrofit getClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request request = chain.request().newBuilder().addHeader(
                    "Authorization", "Bearer " + Constants.user.getToken()).build();
            return chain.proceed(request);
        });

        if (retrofit == null)
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }
}
