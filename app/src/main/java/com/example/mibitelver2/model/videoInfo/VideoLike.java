package com.example.mibitelver2.model.videoInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoLike {
    @SerializedName("liked")
    @Expose
    private boolean isLike;

    @SerializedName("idVideo")
    @Expose
    private int videoId;

    @SerializedName("idUser")
    @Expose
    private int userId;

    public VideoLike(boolean isLike, int videoId, int userId) {
        this.isLike = isLike;
        this.videoId = videoId;
        this.userId = userId;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
