package com.example.mibitelver2.model.tumodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HashTag implements Serializable {

    @SerializedName("idHashTag")
    private int idHashtag;

    @SerializedName("title")
    private String title;

    @SerializedName("totalView")
    private int totalView;

    public HashTag() {
    }

    public HashTag(int idHashtag, String title, int totalView) {
        this.idHashtag = idHashtag;
        this.title = title;
        this.totalView = totalView;
    }

    public int getIdHashtag() {
        return idHashtag;
    }

    public void setIdHashtag(int idHashtag) {
        this.idHashtag = idHashtag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalView() {
        return totalView;
    }

    public void setTotalView(int totalView) {
        this.totalView = totalView;
    }
}
