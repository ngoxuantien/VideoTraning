package com.example.mibitelver2.model.tumodel;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("jwt")
    private String token;

    @SerializedName("username")
    private String username;

    @SerializedName("idUser")
    private int idUser;

    @SerializedName("role")
    private String role;

    public UserResponse(String token, String username, int idUser, String role) {
        this.token = token;
        this.username = username;
        this.idUser = idUser;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
