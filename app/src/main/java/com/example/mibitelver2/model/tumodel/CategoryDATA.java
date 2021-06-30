package com.example.mibitelver2.model.tumodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

// khung dữ liệu nhận được
public class CategoryDATA implements Serializable {

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    // list các category thu được
    @SerializedName("data")
    private List<Category> listCategory;

    public CategoryDATA() {
    }

    public CategoryDATA(String code, String message, List<Category> listCategory) {
        this.code = code;
        this.message = message;
        this.listCategory = listCategory;
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

    public List<Category> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<Category> listCategory) {
        this.listCategory = listCategory;
    }
}
