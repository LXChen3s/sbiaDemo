package com.sbia.sbiademo.model;

import java.util.Date;

/**
 *   tb_article表
 */
public class Article {
    /**
     *   文章id
     *   id
     */
    private Integer id;

    /**
     *   文章标题
     *   title
     */
    private String title;

    /**
     *   文章创建时间
     *   creat_date
     */
    private Date creatDate;

    /**
     *   文章创建用户的Id；外键，对应用户表主键
     *   userId
     */
    private Integer userid;

    /**
     *   文章字数
     *   article_words
     */
    private Integer articleWords;

    /**
     *   文章阅读量
     *   article_read
     */
    private Integer articleRead;

    /**
     *   文章是否原创；0不是，1是；
     *   article_original
     */
    private Integer articleOriginal;

    /**
     *   文章是否置顶；1是，0不是
     *   article_topping
     */
    private Integer articleTopping;

    /**
     *   文章内容
     *   content
     */
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getArticleWords() {
        return articleWords;
    }

    public void setArticleWords(Integer articleWords) {
        this.articleWords = articleWords;
    }

    public Integer getArticleRead() {
        return articleRead;
    }

    public void setArticleRead(Integer articleRead) {
        this.articleRead = articleRead;
    }

    public Integer getArticleOriginal() {
        return articleOriginal;
    }

    public void setArticleOriginal(Integer articleOriginal) {
        this.articleOriginal = articleOriginal;
    }

    public Integer getArticleTopping() {
        return articleTopping;
    }

    public void setArticleTopping(Integer articleTopping) {
        this.articleTopping = articleTopping;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}