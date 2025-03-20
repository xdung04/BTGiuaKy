package com.example.project.models;

import com.google.gson.annotations.SerializedName;

public class FoodReponse {
    @SerializedName("avatar")
    private String avatar;

    public FoodReponse(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
