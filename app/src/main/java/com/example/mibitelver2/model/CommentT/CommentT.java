
package com.example.mibitelver2.model.CommentT;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CommentT {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("message")
    @Expose
    private Object message;

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
