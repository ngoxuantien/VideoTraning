package com.example.mibitelver2.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mibitelver2.model.tumodel.VideoN;
import com.example.mibitelver2.repository.VideoRepository;

import java.util.List;

public class VideoViewModel extends ViewModel implements VideoRepository.OnResponseComplete, ViewModelProvider.Factory {

    private MutableLiveData<List<VideoN>> videos = new MutableLiveData<>();
    private VideoRepository videoRepository = new VideoRepository(this);
    private int idCategory = 0;
    private int offset = 0;
    public VideoViewModel(int idCategory, int offset) {
        this.idCategory = idCategory;
        this.offset = offset;
        videoRepository.getListVideoOfCategory(this.idCategory, this.offset);
    }

    public MutableLiveData<List<VideoN>> getListVideo() {
        return videos;
    }

    public void setListVideo(MutableLiveData<List<VideoN>> videos) {
        this.videos = videos;
    }

    @Override
    public void videoListDataAdd(List<VideoN> listVideo) {
        videos.setValue(listVideo);
    }

    @Override
    public void onError(String error) {
        Log.d("error",error);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new VideoViewModel(idCategory, offset);
    }
}
