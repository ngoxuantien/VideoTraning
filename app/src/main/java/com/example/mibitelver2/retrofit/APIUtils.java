package com.example.mibitelver2.retrofit;

public class APIUtils {

    public static com.example.mibitelver2.retrofit.DataClient getData(String base_url){

        return RetrofitClient.getClient(base_url).create(com.example.mibitelver2.retrofit.DataClient.class);

    }
}
