package com.example.mibitelver2.repository;

import com.example.mibitelver2.model.tumodel.HashTag;
import com.example.mibitelver2.model.tumodel.HashTagData;
import com.example.mibitelver2.retrofit.APIUtils;
import com.example.mibitelver2.retrofit.DataClient;
import com.example.mibitelver2.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HashTagRepository {

    private OnResponseComplete onResponseComplete;

    public HashTagRepository(OnResponseComplete onResponseComplete) {
        this.onResponseComplete = onResponseComplete;
    }

    public void getListHashTag(int idVideo){
        DataClient dataClient = APIUtils.getData(Constants.base_url);
        Call<HashTagData> callData = dataClient.getListHashTagByVideoId(idVideo);
        callData.enqueue(new Callback<HashTagData>() {
            @Override
            public void onResponse(Call<HashTagData> call, Response<HashTagData> response) {
                List<HashTag> hashTags = response.body().getListHashTag();
                onResponseComplete.hashTagListDataAdd(hashTags);
            }

            @Override
            public void onFailure(Call<HashTagData> call, Throwable t) {
                onResponseComplete.onError(t.getMessage());
            }
        });
    }
    public interface OnResponseComplete{
        void hashTagListDataAdd(List<HashTag> listHashTag);
        void onError(String error);
    }
}
