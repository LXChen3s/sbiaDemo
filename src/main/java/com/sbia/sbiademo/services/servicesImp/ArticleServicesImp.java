package com.sbia.sbiademo.services.servicesImp;

import com.sbia.sbiademo.mapper.ArticleMapper;
import com.sbia.sbiademo.model.Article;
import com.sbia.sbiademo.model.ArticleWrapper;
import com.sbia.sbiademo.services.services.ArticleServices;
import com.sbia.sbiademo.util.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServicesImp implements ArticleServices {
    @Autowired
    ArticleMapper articleMapper;
    Logger logger=LoggerFactory.getLogger(ArticleServicesImp.class);

    //获取博客列表第一页
    @Override
    public PageBean<ArticleWrapper> getFirstPage() {
        PageBean<ArticleWrapper> pageBean=new PageBean<>();
        //保存当前页数，设置每页记录数
        pageBean.setPageNumber(1);
        pageBean.setPageSize(10);
        pageBean=getWithPage(pageBean);
        int temp=pageBean.getTotal()%pageBean.getPageSize();
        int totalPage=pageBean.getTotal()/pageBean.getPageSize();
        pageBean.setTotalPage(temp==0?totalPage:totalPage+1);
        return pageBean;
    }
    //获取指定页数博客
    @Override
    public PageBean<ArticleWrapper> getWithPage(PageBean<ArticleWrapper> pageBean) {
        //设定每页数量为10
        pageBean.setPageSize(10);
        //设置起始索引
        pageBean.setStartIndex((pageBean.getPageNumber()-1)*10);
        if(pageBean.getTotal()==null){
            pageBean.setTotal(articleMapper.selectWithCount());
        }
        pageBean.setTlist(articleMapper.selectWithPage(pageBean));
        String abs;
        for(ArticleWrapper articleWrapper:pageBean.getTlist()){
            abs=articleWrapper.getContent().substring(0,articleWrapper.getContent().length()>3000?3000:articleWrapper.getContent().length());
            //去除所有html，去除字符串中的空格,回车,换行符,制表符
            articleWrapper.setAbs(abs.replaceAll("</?[^>]+>", "").replaceAll("\\s*|\t|\r|\n", ""));
        }
        return pageBean;
    }

    @Override
    public ArticleWrapper getArticleContent(Integer id) {
        return articleMapper.selectArticleById(id);
    }
    //添加或更新博客
    @Override
    public void saveArticle(Article article) {
        if(article.getId()!=null){
            //更新
            articleMapper.updateByPrimaryKey(article);
        }
        else{
            //添加
            article.setUserid(6);
            articleMapper.insert(article);
        }
    }
    //删除操作
    @Override
    public void deleteArticle(Integer id) {
        articleMapper.deleteByPrimaryKey(id);
    }
    /*
     * 获取首页展示文章
     */
    public List<ArticleWrapper> getIndexArticles(){
        List<ArticleWrapper> old=articleMapper.selectIndexArticles();
        String abs;
        for(ArticleWrapper articleWrapper:old){
            abs=articleWrapper.getContent().substring(0,articleWrapper.getContent().length()>3000?3000:articleWrapper.getContent().length());
            //去除所有html，去除字符串中的空格,回车,换行符,制表符
            articleWrapper.setAbs(abs.replaceAll("</?[^>]+>", "").replaceAll("\\s*|\t|\r|\n", ""));
        }
        return old;
    }
}
