
package com.example.mibitelver2.modeltien.responselikecommnet;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("idComment")
    @Expose
    private Integer idComment;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("like")
    @Expose
    private Integer like;
    @SerializedName("createAt")
    @Expose
    private String createAt;
    @SerializedName("updateAt")
    @Expose
    private String updateAt;
    @SerializedName("discriminator")
    @Expose
    private String discriminator;
    @SerializedName("userResponseDto")
    @Expose
    private Object userResponseDto;
    @SerializedName("videoResponseDto")
    @Expose
    private Object videoResponseDto;
    @SerializedName("isLiked")
    @Expose
    private Integer isLiked;

    public Integer getIdComment() {
        return idComment;
    }

    public void setIdComment(Integer idComment) {
        this.idComment = idComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
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

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public Object getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(Object userResponseDto) {
        this.userResponseDto = userResponseDto;
    }

    public Object getVideoResponseDto() {
        return videoResponseDto;
    }

    public void setVideoResponseDto(Object videoResponseDto) {
        this.videoResponseDto = videoResponseDto;
    }

    public Integer getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(Integer isLiked) {
        this.isLiked = isLiked;
    }

}
