package com.example.APIGiuaKy.DTO;

public class AccountRequest {
    private String username;
    private String email;
    private String password;
    private String avatar;
    private String gender;

    public AccountRequest(String username, String email, String password, String avatar, String gender) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.gender = gender;
    }

    public AccountRequest() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

