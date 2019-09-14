package com.sbia.sbiademo.services.services;

import com.sbia.sbiademo.model.Article;
import com.sbia.sbiademo.model.ArticleWrapper;
import com.sbia.sbiademo.util.PageBean;

import java.util.List;


public interface ArticleServices {
    PageBean<ArticleWrapper> getFirstPage();
    PageBean<ArticleWrapper> getWithPage(PageBean<ArticleWrapper> pageBean);
    List<ArticleWrapper> getIndexArticles();
    ArticleWrapper getArticleContent(Integer id);
    void saveArticle(Article article);
    void deleteArticle(Integer id);
}
