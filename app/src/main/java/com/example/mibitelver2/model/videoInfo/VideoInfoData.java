package com.example.mibitelver2.model.videoInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoInfoData {
    @SerializedName("idVideo")
    @Expose
    private int idVideo;

    @SerializedName("idUser")
    @Expose
    private int idUser;

    @SerializedName("idUserVideo")
    @Expose
    private int idUserVideo;

    @SerializedName("liked")
    @Expose
    private boolean isLiked;

    @SerializedName("watch")
    @Expose
    private boolean isWatch;

    @SerializedName("watchLater")
    @Expose
    private boolean isWatchLater;

    @SerializedName("timeWatch")
    @Expose
    private String timeWatch;

    @SerializedName("timeStop")
    @Expose
    private String timeStop;

    @SerializedName("totalTime")
    @Expose
    private String totalTime;

    @SerializedName("createAt")
    @Expose
    private String createAt;

    @SerializedName("updateAt")
    @Expose
    private String updateAt;

    public int getIdUserVideo() {
        return idUserVideo;
    }

    public void setIdUserVideo(int idUserVideo) {
        this.idUserVideo = idUserVideo;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isWatch() {
        return isWatch;
    }

    public void setWatch(boolean watch) {
        isWatch = watch;
    }

    public String getTimeWatch() {
        return timeWatch;
    }

    public void setTimeWatch(String timeWatch) {
        this.timeWatch = timeWatch;
    }

    public String getTimeStop() {
        return timeStop;
    }

    public void setTimeStop(String timeStop) {
        this.timeStop = timeStop;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public boolean isWatchLater() {
        return isWatchLater;
    }

    public void setWatchLater(boolean watchLater) {
        isWatchLater = watchLater;
    }
}
