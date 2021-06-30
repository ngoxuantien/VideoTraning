package com.example.mibitelver2.model.videoInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoInfo {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private VideoInfoData data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public VideoInfoData getData() {
        return data;
    }

    public void setData(VideoInfoData data) {
        this.data = data;
    }
}
