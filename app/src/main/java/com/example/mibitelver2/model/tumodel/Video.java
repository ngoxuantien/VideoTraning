package com.example.mibitelver2.model.tumodel;

import java.io.Serializable;

public class Video implements Serializable {
    private int idVideo;
    private String thumbnail;
    private String imgAvata;
    private String nameVideo;
    private String nameUser;
    private String hashTag;
    private int view;
    private String timeLine;

    public Video() {
    }

    public Video(int idVideo, String thumbnail, String imgAvata,
                 String nameVideo, String nameUser, String hashTag, int view, String timeLine) {
        this.idVideo = idVideo;
        this.thumbnail = thumbnail;
        this.imgAvata = imgAvata;
        this.nameVideo = nameVideo;
        this.nameUser = nameUser;
        this.hashTag = hashTag;
        this.view = view;
        this.timeLine = timeLine;
    }

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImgAvata() {
        return imgAvata;
    }

    public void setImgAvata(String imgAvata) {
        this.imgAvata = imgAvata;
    }

    public String getNameVideo() {
        return nameVideo;
    }

    public void setNameVideo(String nameVideo) {
        this.nameVideo = nameVideo;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(String timeLine) {
        this.timeLine = timeLine;
    }
}
