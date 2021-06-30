package com.example.mibitelver2.model.channel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.mibitelver2.util.GlideLoader;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChannelData {

    @SerializedName("idUser")
    @Expose
    private int idUser;

    @SerializedName("fullName")
    @Expose
    private String fullName;

    @SerializedName("nickName")
    @Expose
    private String nickName;

    @SerializedName("photo")
    @Expose
    private String photo;

    @SerializedName("muralPhoto")
    @Expose
    private String muralPhoto;

    @SerializedName("shortBiography")
    @Expose
    private String shortBiography;

    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("totalLike")
    @Expose
    private int totalLike;

    @SerializedName("totalFollower")
    @Expose
    private int totalFollower;

    public int getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(int totalLike) {
        this.totalLike = totalLike;
    }

    public int getTotalFollower() {
        return totalFollower;
    }

    public void setTotalFollower(int totalFollower) {
        this.totalFollower = totalFollower;
    }

    public int getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(int totalViews) {
        this.totalViews = totalViews;
    }

    @SerializedName("totalViews")
    @Expose
    private int totalViews;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMuralPhoto() {
        return muralPhoto;
    }

    public void setMuralPhoto(String muralPhoto) {
        this.muralPhoto = muralPhoto;
    }

    public String getShortBiography() {
        return shortBiography;
    }

    public void setShortBiography(String shortBiography) {
        this.shortBiography = shortBiography;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

//    @BindingAdapter("userImage")
//    public static void loadImage(ImageView view, String userImg) {
//        GlideLoader glideLoader = new GlideLoader();
//        glideLoader.loadPicture(userImg, view, view.getContext());
//    }
}
