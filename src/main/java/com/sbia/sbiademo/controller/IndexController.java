package com.sbia.sbiademo.controller;

import com.sbia.sbiademo.model.ArticleWrapper;
import com.sbia.sbiademo.model.wrapper.CategoryWrapper;
import com.sbia.sbiademo.services.services.ArticleServices;
import com.sbia.sbiademo.services.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sbiademo/")
public class IndexController {
    @Autowired
    ArticleServices articleServices;
    @Autowired
    CategoryServices categoryServices;

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String getIndex(ModelMap modelMap){
        List<ArticleWrapper> articles=articleServices.getIndexArticles();
        modelMap.addAttribute("article1",articles.get(0));
        modelMap.addAttribute("article2",articles.get(1));
        modelMap.addAttribute("article3",articles.get(2));
        modelMap.addAttribute("article4",articles.get(3));
        modelMap.addAttribute("article5",articles.get(4));
        List<CategoryWrapper> categorys=categoryServices.selectWithCount();
        modelMap.addAttribute("categorys",categorys);
        return "views/index";
    }
}
