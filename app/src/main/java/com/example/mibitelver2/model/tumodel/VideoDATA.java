package com.example.mibitelver2.model.tumodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoDATA {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("data")
    @Expose
    private List<VideoN> listVideo = null;

    public VideoDATA() {
    }

    public VideoDATA(String code, Object message, List<VideoN> listVideo) {
        this.code = code;
        this.message = message;
        this.listVideo = listVideo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<VideoN> getListVideo() {
        return listVideo;
    }

    public void setListVideo(List<VideoN> listVideo) {
        this.listVideo = listVideo;
    }
}
