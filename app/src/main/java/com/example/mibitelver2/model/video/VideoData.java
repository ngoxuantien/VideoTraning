package com.example.mibitelver2.model.video;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.mibitelver2.util.GlideLoader;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoData {
    @SerializedName("idVideo")
    @Expose
    private int idVideo;

    @SerializedName("file")
    @Expose
    private String file;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("inRecommend")
    @Expose
    private boolean inRecommend;

    @SerializedName("quality")
    @Expose
    private String quality;

    @SerializedName("totalLike")
    @Expose
    private int totalLike;

    @SerializedName("totalComment")
    @Expose
    private int totalComment;

    @SerializedName("timeAverage")
    @Expose
    private double timeAverage;

    @SerializedName("totalView")
    @Expose
    private int totalView;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("category_id")
    @Expose
    private String category_id;

    @SerializedName("report_id")
    @Expose
    private String report_id;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("linkVideo")
    @Expose
    private String linkVideo;

    @SerializedName("linkHorizontalCoverImage")
    @Expose
    private String linkHorizontalCoverImage;

    @SerializedName("linkVerticalCoverImage")
    @Expose
    private String linkVerticalCoverImage;

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isInRecommend() {
        return inRecommend;
    }

    public void setInRecommend(boolean inRecommend) {
        this.inRecommend = inRecommend;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(int totalLike) {
        this.totalLike = totalLike;
    }

    public int getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(int totalComment) {
        this.totalComment = totalComment;
    }

    public double getTimeAverage() {
        return timeAverage;
    }

    public void setTimeAverage(double timeAverage) {
        this.timeAverage = timeAverage;
    }

    public int getTotalView() {
        return totalView;
    }

    public void setTotalView(int totalView) {
        this.totalView = totalView;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getReport_id() {
        return report_id;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public String getLinkHorizontalCoverImage() {
        return linkHorizontalCoverImage;
    }

    public void setLinkHorizontalCoverImage(String linkHorizontalCoverImage) {
        this.linkHorizontalCoverImage = linkHorizontalCoverImage;
    }

    public String getLinkVerticalCoverImage() {
        return linkVerticalCoverImage;
    }

    public void setLinkVerticalCoverImage(String linkVerticalCoverImage) {
        this.linkVerticalCoverImage = linkVerticalCoverImage;
    }

    @BindingAdapter("videoImage")
    public static void loadImage(ImageView view, String imgUrl) {
        GlideLoader glideLoader = new GlideLoader();
        glideLoader.loadPicture(imgUrl, view, view.getContext());
    }
}
