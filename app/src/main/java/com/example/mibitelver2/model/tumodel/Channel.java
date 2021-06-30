package com.example.mibitelver2.model.tumodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Channel implements Serializable {

    private int idChannel;

    @SerializedName("image")
    private int image;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("regisChannel")
    private int regisChannel;

    public Channel(int id, int image, String name, String description, int regisChannel) {
        this.idChannel = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.regisChannel = regisChannel;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRegisChannel() {
        return regisChannel;
    }

    public void setRegisChannel(int regisChannel) {
        this.regisChannel = regisChannel;
    }

    public int getIdChannel() {
        return idChannel;
    }

    public void setIdChannel(int idChannel) {
        this.idChannel = idChannel;
    }
}
