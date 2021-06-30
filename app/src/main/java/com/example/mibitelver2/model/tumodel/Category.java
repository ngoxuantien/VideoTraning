package com.example.mibitelver2.model.tumodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {

    // Danh mục dữ liệu nhận về, được chứa trong data của model CategoryData
    @SerializedName("idCategory")
    private int id;

    @SerializedName("logo")
    private String image;

    @SerializedName("color")
    private String color;

    @SerializedName("name")
    private String name;

    public Category() {
    }

    public Category(int id, String image, String color, String name) {
        this.id = id;
        this.image = image;
        this.color = color;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category(String image, String color, String name) {
        this.image = image;
        this.color = color;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
