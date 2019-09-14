package com.sbia.sbiademo.controller;

import com.alibaba.fastjson.JSON;
import com.sbia.sbiademo.model.Article;
import com.sbia.sbiademo.model.ArticleWrapper;
import com.sbia.sbiademo.services.services.ArticleServices;
import com.sbia.sbiademo.util.PageBean;
import com.sbia.sbiademo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/sbiademo/")
public class ArticleController {
    @Autowired
    ArticleServices articleServices;

    //返回文章列表首页
    @RequestMapping(value = "articlesIndex",method = RequestMethod.GET)
    public String getArticles(ModelMap modelMap){
        PageBean<ArticleWrapper> pageBean=articleServices.getFirstPage();
        modelMap.addAttribute("articles",pageBean.getTlist());
        modelMap.addAttribute("totalPage",pageBean.getTotalPage());
        return "views/articles/article_list";
    }
    //返回指定页数数据
    //produces只处理请求接收数据为json的请求
    @RequestMapping(value = "articles",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public String getArticles(@RequestBody Map<String, Object> models){
        PageBean<ArticleWrapper> pageBean=new PageBean<>();
        pageBean.setPageNumber(StringUtils.stringToInteger(models.get("pageNumber").toString()));
        Integer i=StringUtils.stringToInteger(models.get("totalPage").toString());
        if(i!=null){
            pageBean.setTotalPage(i);
        }
        pageBean=articleServices.getWithPage(pageBean);
        String pageBeanJson=JSON.toJSON(pageBean).toString();
        //字符串转换成json字符串
        return JSON.toJSONString("{\"pageBean\":"+pageBeanJson+"}");
    }
    //返回具体页文章内容
    @RequestMapping(value = "articleContent/{articleId}",method = RequestMethod.GET)
    public String getArticleContent(@PathVariable("articleId") int articleId,ModelMap modelMap){
        modelMap.addAttribute("articleContent",articleServices.getArticleContent(articleId));
        return "views/articles/article_content";
    }
    //返回后台博客列表首页
    @RequestMapping(value = "admin/articlesIndex",method = RequestMethod.GET)
    public String getAdminArticles(ModelMap modelMap){
        PageBean<ArticleWrapper> pageBean=articleServices.getFirstPage();
        modelMap.addAttribute("articles",pageBean.getTlist());
        modelMap.addAttribute("totalPage",pageBean.getTotalPage());
        return "views/articles/admin/article_list";
    }
    //返回后台更新博客页面
    @RequestMapping(value = "admin/changeArticle",method = RequestMethod.GET)
    public String getAdminChangeArticle(@RequestParam("id") Integer id,ModelMap modelMap){
        if(id!=-1){
            modelMap.addAttribute("articleContent",articleServices.getArticleContent(id));
        }
        return "views/articles/admin/change_article";
    }
    //返回后台更新博客页面
    @RequestMapping(value = "admin/saveArticle",method = RequestMethod.POST)
    public String saveArticle(Article article){
        articleServices.saveArticle(article);
        return "redirect:/sbiademo/admin/articlesIndex";
    }
    //返回后台更新博客页面
    @RequestMapping(value = "admin/deleteArticle",method = RequestMethod.GET)
    @ResponseBody
    public String deleteArticle(@RequestParam("id") Integer id,
                                @RequestParam("pageNum") Integer pageNum,
                                @RequestParam("totalPage") Integer totalPage){
        articleServices.deleteArticle(id);
        PageBean<ArticleWrapper> pageBean=new PageBean<>();
        pageBean.setTotalPage(totalPage);
        pageBean.setPageNumber(pageNum);
        pageBean=articleServices.getWithPage(pageBean);
        String pageBeanJson=JSON.toJSON(pageBean).toString();
        //字符串转换成json字符串
        return JSON.toJSONString("{\"pageBean\":"+pageBeanJson+"}");
    }
}
