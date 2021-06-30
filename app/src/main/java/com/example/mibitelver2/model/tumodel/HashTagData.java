package com.example.mibitelver2.model.tumodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HashTagData implements Serializable {
    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    // list các category thu được
    @SerializedName("data")
    private List<HashTag> listHashTag;

    public HashTagData() {
    }

    public HashTagData(String code, String message, List<HashTag> listHashTag) {
        this.code = code;
        this.message = message;
        this.listHashTag = listHashTag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HashTag> getListHashTag() {
        return listHashTag;
    }

    public void setListHashTag(List<HashTag> listHashTag) {
        this.listHashTag = listHashTag;
    }
}
