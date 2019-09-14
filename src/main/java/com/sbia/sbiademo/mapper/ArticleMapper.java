package com.sbia.sbiademo.mapper;

import com.sbia.sbiademo.model.Article;
import com.sbia.sbiademo.model.ArticleWrapper;
import com.sbia.sbiademo.util.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    Article selectByPrimaryKey(Integer id);

    List<Article> selectAll();

    int updateByPrimaryKey(Article record);
    //分页查询
    List<ArticleWrapper> selectWithPage(PageBean<ArticleWrapper> pageBean);
    int selectWithCount();

    List<ArticleWrapper> selectIndexArticles();
    ArticleWrapper selectArticleById(int id);
}