package com.example.mibitelver2.model.allHashtag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HashtagData {

    @SerializedName("idHashTag")
    @Expose
    private int idHashTag;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("createAt")
    @Expose
    private String createAt;

    @SerializedName("updateAt")
    @Expose
    private String updateAt;

    @SerializedName("totalView")
    @Expose
    private int totalView;

    public int getIdHashTag() {
        return idHashTag;
    }

    public void setIdHashTag(int idHashTag) {
        this.idHashTag = idHashTag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getTotalView() {
        return totalView;
    }

    public void setTotalView(int totalView) {
        this.totalView = totalView;
    }
}
