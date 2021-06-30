package com.example.mibitelver2.repository;

import com.example.mibitelver2.model.tumodel.ChannelListDATA;
import com.example.mibitelver2.model.tumodel.ChannelN;
import com.example.mibitelver2.retrofit.APIUtils;
import com.example.mibitelver2.retrofit.DataClient;
import com.example.mibitelver2.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChannelListRepository {

    private OnResponseComplete onResponseComplete;

    public ChannelListRepository(OnResponseComplete onResponseComplete) {
        this.onResponseComplete = onResponseComplete;
    }

    // lấy list channel của 1 category cho người dùng đăng kí - gd03
    public void getListChannelOfCategory(int idCategory){
        DataClient dataClient = APIUtils.getData(Constants.base_url);
        Call<ChannelListDATA> getListChannel = dataClient.getChannelList(idCategory);
        getListChannel.enqueue(new Callback<ChannelListDATA>() {
            @Override
            public void onResponse(Call<ChannelListDATA> call, Response<ChannelListDATA> response) {
                onResponseComplete.channelListDataAdd(response.body().getListChannelN());
            }

            @Override
            public void onFailure(Call<ChannelListDATA> call, Throwable t) {
                onResponseComplete.onError(t.getMessage());
            }
        });
    }

    public interface OnResponseComplete{
        void channelListDataAdd(List<ChannelN> listChannelN);
        void onError(String error);
    }
}
