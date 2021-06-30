package com.example.mibitelver2.model.tumodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChannelPost {

    @SerializedName("idUser")
    private int idUser;

    @SerializedName("listChannels")
    private List<Integer> listChannelsPost;

    public ChannelPost(int idUser, List<Integer> listChannelsPost) {
        this.idUser = idUser;
        this.listChannelsPost = listChannelsPost;
    }

    public ChannelPost() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public List<Integer> getListChannelsPost() {
        return listChannelsPost;
    }

    public void setListChannelsPost(List<Integer> listChannelsPost) {
        this.listChannelsPost = listChannelsPost;
    }
}
