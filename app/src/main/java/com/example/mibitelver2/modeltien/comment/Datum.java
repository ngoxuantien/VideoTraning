
package com.example.mibitelver2.modeltien.comment;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Datum {

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




    private UserResponseDto userResponseDto;
    @SerializedName("isLiked")
    @Expose
    private  int isLike;

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public Datum(Integer idComment, String content, Integer like, String createAt, String updateAt, String discriminator, UserResponseDto userResponseDto, Object videoResponseDto) {
        this.idComment = idComment;
        this.content = content;
        this.like = like;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.discriminator = discriminator;
        this.userResponseDto = userResponseDto;
        this.videoResponseDto = videoResponseDto;
    }

    public Datum(Integer idComment, String content, Integer like, String createAt, String updateAt, String discriminator) {
        this.idComment = idComment;
        this.content = content;
        this.like = like;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.discriminator = discriminator;
    }


    @SerializedName("videoResponseDto")
    @Expose

    private Object videoResponseDto;

    public String convertLike() {
        return this.like + "";
    }

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

    public String TextTime() {

        Calendar c = Calendar.getInstance();
        String strDt = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

        try {
            Date datetime = format.parse(this.updateAt);
            c.setTime(datetime);

        } catch (Exception e) {
            e.printStackTrace();


        }
        Long k = c.getTimeInMillis() / 1000L;
        Calendar calendar = Calendar.getInstance();
        Long g = calendar.getTimeInMillis() / 1000L;

        int d = (int) (g - k);
        String m = "";
        if (d < 60) {
            m = "V??i gi??y tr?????c";
        } else {
            if (d >= 60 && d < 60 * 60) {
                m = d / 60 + " ph??t";
            } else {
                if (d >= 60 * 60 && d < 60 * 60 * 24) {
                    m = d / (60 * 60) + " gi???";
                } else {
                    if (d >= 60 * 60 * 24 && d < 60 * 60 * 24 * 7) {
                        m = d / (60 * 60 * 24) + " tu???n";
                    } else {
                        if (d >= 60 * 60 * 24 * 365) {
                            m = d / (60 * 60 * 24 * 365) + " n??m";
                        }
                    }
                }
            }

        }
        return m;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }

    public Object getVideoResponseDto() {
        return videoResponseDto;
    }

    public void setVideoResponseDto(Object videoResponseDto) {
        this.videoResponseDto = videoResponseDto;
    }

}
