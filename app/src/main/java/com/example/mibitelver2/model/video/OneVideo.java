package com.example.mibitelver2.model.video;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OneVideo {
    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("message")
    @Expose
    private Object message;

    @SerializedName("data")
    @Expose
    private VideoData data;

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

    public VideoData getData() {
        return data;
    }

    public void setData(VideoData data) {
        this.data = data;
    }
}
