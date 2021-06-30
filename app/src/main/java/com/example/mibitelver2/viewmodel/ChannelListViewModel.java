package com.example.mibitelver2.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mibitelver2.model.tumodel.ChannelN;
import com.example.mibitelver2.repository.ChannelListRepository;

import java.util.List;

//gd03
public class ChannelListViewModel extends ViewModel implements ChannelListRepository.OnResponseComplete, ViewModelProvider.Factory {

    private int idCategory;
    private MutableLiveData<List<ChannelN>> listChannels = new MutableLiveData<>();
    private ChannelListRepository channelListRepository = new ChannelListRepository(this);

    public ChannelListViewModel(int idCategory) {
        this.idCategory = idCategory;
        channelListRepository.getListChannelOfCategory(this.idCategory);
    }

    public MutableLiveData<List<ChannelN>> getListChannels() {
        return listChannels;
    }

    public void setListChannels(MutableLiveData<List<ChannelN>> listChannels) {
        this.listChannels = listChannels;
    }

    @Override
    public void channelListDataAdd(List<ChannelN> listChannelN) {
        listChannels.setValue(listChannelN);
    }

    @Override
    public void onError(String error) {

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ChannelListViewModel(idCategory);
    }
}
