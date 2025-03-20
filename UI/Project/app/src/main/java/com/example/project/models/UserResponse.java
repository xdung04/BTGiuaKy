package com.example.project.models;

public class UserResponse {
    private  String username;
    private Long id;
    private String email;
    private String password;
    private String gender;
    private String avatar;

    public UserResponse(String username, String avatar) {
        this.username = username;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
