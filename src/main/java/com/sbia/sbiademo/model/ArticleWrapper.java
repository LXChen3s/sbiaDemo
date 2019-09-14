package com.sbia.sbiademo.model;

public class ArticleWrapper extends Article {
    /**
     * 文章的简介
     */
    private String abs;
    /**
     * 文章所属用户
     */
    private User user;
    /**
     * 文章所属类别
     */
    private Category category;

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
