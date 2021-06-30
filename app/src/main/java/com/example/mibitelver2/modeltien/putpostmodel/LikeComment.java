package com.example.mibitelver2.modeltien.putpostmodel;

public class LikeComment {
    private int idComment,userId;
    private boolean like;

    public LikeComment(int idComment, int userId, boolean like) {
        this.idComment = idComment;
        this.userId = userId;
        this.like = like;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
