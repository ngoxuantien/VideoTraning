package com.example.mibitelver2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mibitelver2.model.videoInfo.VideoInfo;
import com.example.mibitelver2.modeltien.channel.Channel;
import com.example.mibitelver2.modeltien.hashtag.HashTag;
import com.example.mibitelver2.modeltien.putpostmodel.Likeput;
import com.example.mibitelver2.modeltien.putpostmodel.PostFollower;
import com.example.mibitelver2.modeltien.putpostmodel.WhatLatePut;
import com.example.mibitelver2.modeltien.video.VideoAcount;
import com.example.mibitelver2.repository.MovieRepository;

public class VideoUserViewModel extends AndroidViewModel {
    public MutableLiveData<VideoAcount> videoAcount12 = new MutableLiveData<>();
    public MutableLiveData<Channel> channel = new MutableLiveData<>();
    public MutableLiveData<HashTag> hashTag = new MutableLiveData<>();
    public MutableLiveData<VideoInfo> videoInfor = new MutableLiveData<>();

    private MovieRepository movieRepository = new MovieRepository();

    public VideoUserViewModel(@NonNull Application application) {
        super(application);
    }



//get API
    public int getIdvideo() {
        return videoAcount12.getValue().getData().getIdVideo();
    }

    public void getVideoInfor(int idVideo, int idUser) {
        videoInfor = movieRepository.getVideoInfor(idVideo, idUser);
    }

    public void getVideoAcount(String idVideo, String idUser) {
        videoAcount12 = movieRepository.getVideo(idVideo, idUser);
    }

    public void getChannelVideo(String id, String idUser) {
        channel = movieRepository.getChannel(id, idUser);
    }

    public void getHashTag(String id) {
        hashTag = movieRepository.getHashTag(id);
    }


 //put API
    public void putLike(Likeput likeput) {
        movieRepository.putLike(likeput);
    }

    public void putWhatLate(WhatLatePut whatLatePut) {
        movieRepository.putWhatLate(whatLatePut);
    }

    public void postFollower(PostFollower postFollower) {
        movieRepository.postFollower(postFollower);
    }


//delete API
    public void deleteFollower(PostFollower postFollower) {
        movieRepository.deleteFollower(postFollower);
    }


}
