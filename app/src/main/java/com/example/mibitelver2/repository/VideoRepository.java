package com.example.mibitelver2.repository;

import com.example.mibitelver2.model.tumodel.VideoDATA;
import com.example.mibitelver2.model.tumodel.VideoN;
import com.example.mibitelver2.retrofit.APIUtils;
import com.example.mibitelver2.retrofit.DataClient;
import com.example.mibitelver2.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoRepository {

    private OnResponseComplete onResponseComplete;

    public VideoRepository(OnResponseComplete onResponseComplete) {
        this.onResponseComplete = onResponseComplete;
    }

    // lấy list video của 1 category
    public void getListVideoOfCategory(int idCategory, int offset){
        DataClient dataClient = APIUtils.getData(Constants.base_url);
        Call<VideoDATA> getVideos = dataClient.getListVideoByCategoryId(idCategory, offset,10);
        getVideos.enqueue(new Callback<VideoDATA>() {
            @Override
            public void onResponse(Call<VideoDATA> call, Response<VideoDATA> response) {
                onResponseComplete.videoListDataAdd(response.body().getListVideo());
            }

            @Override
            public void onFailure(Call<VideoDATA> call, Throwable t) {
                onResponseComplete.onError(t.getMessage());
            }
        });

    }
    public interface OnResponseComplete{
        void videoListDataAdd(List<VideoN> listVideo);
        void onError(String error);
    }
}
