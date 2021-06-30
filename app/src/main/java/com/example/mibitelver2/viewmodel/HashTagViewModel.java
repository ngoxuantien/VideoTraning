package com.example.mibitelver2.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mibitelver2.model.tumodel.HashTag;
import com.example.mibitelver2.repository.HashTagRepository;

import java.util.List;

public class HashTagViewModel extends ViewModel implements HashTagRepository.OnResponseComplete, ViewModelProvider.Factory {

    private HashTagRepository hashTagRepository = new HashTagRepository(this);
    private MutableLiveData<List<HashTag>> hashtags = new MutableLiveData<>();
    private int idVideo;

    public HashTagViewModel(int idVideo) {
        this.idVideo = idVideo;
        hashTagRepository.getListHashTag(this.idVideo);
    }

    public HashTagRepository getHashTagRepository() {
        return hashTagRepository;
    }

    public void setHashTagRepository(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    public MutableLiveData<List<HashTag>> getHashtags() {
        return hashtags;
    }

    public void setHashtags(MutableLiveData<List<HashTag>> hashtags) {
        this.hashtags = hashtags;
    }

    @Override
    public void hashTagListDataAdd(List<HashTag> listHashTag) {
        this.hashtags.setValue(listHashTag);
    }

    @Override
    public void onError(String error) {
        Log.d("error", error);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HashTagViewModel(idVideo);
    }
}
