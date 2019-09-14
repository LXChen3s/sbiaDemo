package com.sbia.sbiademo.model;

/**
 *   tb_category表
 */
public class Category {
    /**
     *   类别表主键
     *   id
     */
    private Integer id;

    /**
     *   类别名称
     *   category_name
     */
    private String categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}