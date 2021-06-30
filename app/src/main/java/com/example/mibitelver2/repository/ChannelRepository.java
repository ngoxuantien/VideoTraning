package com.example.mibitelver2.repository;

import com.example.mibitelver2.model.tumodel.ChannelDATA;
import com.example.mibitelver2.model.tumodel.ChannelN;
import com.example.mibitelver2.retrofit.APIUtils;
import com.example.mibitelver2.retrofit.DataClient;
import com.example.mibitelver2.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChannelRepository {

    private OnResponseComplete onResponseComplete;
    public ChannelRepository(OnResponseComplete onResponseComplete) {
        this.onResponseComplete = onResponseComplete;
    }

    // Lấy thông tin channel của video
    public void getChannelOfVideo(int idVideo, int idUser){
        DataClient dataClient = APIUtils.getData(Constants.base_url);
        Call<ChannelDATA> getChannel = dataClient.getChannelOfVideo(idVideo, idUser);
        getChannel.enqueue(new Callback<ChannelDATA>() {
            @Override
            public void onResponse(Call<ChannelDATA> call, Response<ChannelDATA> response) {
                onResponseComplete.channelListDataAdd(response.body().getChannel());
            }

            @Override
            public void onFailure(Call<ChannelDATA> call, Throwable t) {
                onResponseComplete.onError(t.getMessage());
            }
        });
    }


    public interface OnResponseComplete{
        void channelListDataAdd(ChannelN channelN);
        void onError(String error);
    }


}
