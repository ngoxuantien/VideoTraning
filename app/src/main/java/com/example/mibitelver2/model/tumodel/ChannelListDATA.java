package com.example.mibitelver2.model.tumodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// lấy list channel của 1 category - màn hình 03
public class ChannelListDATA {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("data")
    @Expose
    private List<ChannelN> listChannelN;

    public ChannelListDATA() {
    }

    public ChannelListDATA(String code, Object message, List<ChannelN> listChannelN) {
        this.code = code;
        this.message = message;
        this.listChannelN = listChannelN;
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

    public List<ChannelN> getListChannelN() {
        return listChannelN;
    }

    public void setListChannelN(List<ChannelN> listChannelN) {
        this.listChannelN = listChannelN;
    }
}
