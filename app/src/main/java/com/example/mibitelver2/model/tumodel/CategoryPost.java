package com.example.mibitelver2.model.tumodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

// form dữ liệu post lên server
public class CategoryPost implements Serializable {

    // id người dùng
    @SerializedName("user_id")
    private int userId;

    // list các lựa chọn category
    @SerializedName("listCate")
    private List<Integer> categoriesIdPost;

    public CategoryPost(int userId, List<Integer> categoriesIdPost) {
        this.userId = userId;
        this.categoriesIdPost = categoriesIdPost;
    }

    public CategoryPost() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Integer> getCategoriesIdPost() {
        return categoriesIdPost;
    }

    public void setCategoriesIdPost(List<Integer> categoriesIdPost) {
        this.categoriesIdPost = categoriesIdPost;
    }
}
