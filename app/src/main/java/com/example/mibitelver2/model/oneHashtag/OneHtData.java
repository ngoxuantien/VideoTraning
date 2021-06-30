package com.example.mibitelver2.model.oneHashtag;

import com.example.mibitelver2.model.video.VideoData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OneHtData {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("video")
    @Expose
    private VideoData videoData;

    @SerializedName("hashtag")
    @Expose
    private String hashtag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VideoData getVideoData() {
        return videoData;
    }

    public void setVideoData(VideoData videoData) {
        this.videoData = videoData;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}
