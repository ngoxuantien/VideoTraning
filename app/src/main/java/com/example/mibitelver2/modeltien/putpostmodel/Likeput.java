package com.example.mibitelver2.modeltien.putpostmodel;

public class Likeput {
    private int liked;
    private int idVideo;
    private int idUser;

    public int getIsLiked() {
        return liked;
    }

    public void setIsLiked(int isLiked) {
        this.liked = isLiked;
    }

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Likeput(int isLiked, int idVideo, int idUser) {
        this.liked = isLiked;
        this.idVideo = idVideo;
        this.idUser = idUser;
    }




}
