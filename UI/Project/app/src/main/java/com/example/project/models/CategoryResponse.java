package com.example.project.models;

public class CategoryResponse {
    private Long id;

    private String avatar;

    private String categoryName;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryResponse(String avatar, String categoryName) {
        this.avatar = avatar;
        this.categoryName = categoryName;
    }
}
