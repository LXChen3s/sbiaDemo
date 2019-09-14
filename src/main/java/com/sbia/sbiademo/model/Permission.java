package com.sbia.sbiademo.model;

/**
 *   u_permission表
 */
public class Permission {
    /**
     *   id
     */
    private Long id;

    /**
     *   url地址
     *   url
     */
    private String url;

    /**
     *   url描述
     *   name
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}