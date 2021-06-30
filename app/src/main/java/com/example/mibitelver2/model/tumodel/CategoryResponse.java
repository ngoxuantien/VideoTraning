
package com.example.mibitelver2.model.tumodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

// response sau khi post category post lên sever, khi ng dùng đã chọn xong danh mục yêu thích
public class CategoryResponse implements Serializable {
    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    // list các category thu được
    @SerializedName("data")
    private List<Integer> categoriesId;

    public CategoryResponse() {
    }

    public CategoryResponse(String code, String message, List<Integer> categoriesId) {
        this.code = code;
        this.message = message;
        this.categoriesId = categoriesId;
    }

    public List<Integer> getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(List<Integer> categoriesId) {
        this.categoriesId = categoriesId;
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

}
