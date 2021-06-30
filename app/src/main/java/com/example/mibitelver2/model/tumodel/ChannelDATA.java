package com.example.mibitelver2.model.tumodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Dùng để lấy thông tin channel của 1 video (trong màn hình home)
public class ChannelDATA {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("data")
    @Expose
    private ChannelN channel;

    public ChannelDATA() {
    }

    public ChannelDATA(String code, Object message, ChannelN channel) {
        this.code = code;
        this.message = message;
        this.channel = channel;
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

    public ChannelN getChannel() {
        return channel;
    }

    public void setChannel(ChannelN channel) {
        this.channel = channel;
    }
}
