package com.example.mibitelver2.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mibitelver2.model.tumodel.ChannelN;
import com.example.mibitelver2.repository.ChannelRepository;

public class ChannelViewModel extends ViewModel implements ChannelRepository.OnResponseComplete, ViewModelProvider.Factory {

    private MutableLiveData<ChannelN> channelLiveData = new MutableLiveData<>();
    private ChannelRepository channelRepository = new ChannelRepository(this);
    private int idVideo;
    private int idUser;
    public ChannelViewModel(int idVideo, int idUser) {
        this.idVideo = idVideo;
        this.idUser = idUser;
        channelRepository.getChannelOfVideo(this.idVideo, this.idUser);
    }

    public MutableLiveData<ChannelN> getChannelLiveData() {
        return channelLiveData;
    }

    public void setChannelLiveData(MutableLiveData<ChannelN> channelLiveData) {
        this.channelLiveData = channelLiveData;
    }

    public ChannelRepository getChannelRepository() {
        return channelRepository;
    }

    public void setChannelRepository(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public void channelListDataAdd(ChannelN channelN) {
        channelLiveData.setValue(channelN);
    }

    @Override
    public void onError(String error) {
        Log.d("error",error);
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ChannelViewModel(idVideo, idUser);
    }
}
