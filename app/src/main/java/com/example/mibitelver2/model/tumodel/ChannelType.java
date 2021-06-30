package com.example.mibitelver2.model.tumodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ChannelType implements Serializable {

    @SerializedName("type")
    private String type;

    @SerializedName("list_channel")
    private List<ChannelN> listChannel;

    public ChannelType(String type, List<ChannelN> listChannel) {
        this.type = type;
        this.listChannel = listChannel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ChannelN> getListChannel() {
        return listChannel;
    }
}
