package com.sbia.sbiademo.controller;

import com.sbia.sbiademo.model.User;
import com.sbia.sbiademo.services.services.UserServices;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/sbiademo/")
public class ShiroController {
    @Autowired
    UserServices userServices;

    //获取注册页面
    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String getRegister(){
        return "views/login/register";
    }
    //进行注册,跳转页面
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String register(@RequestParam("username") String username,
                        @RequestParam("password") String password, ModelMap modelMap){
        User user=new User();
        String error="";
        user.setName(username);
        user.setPassword(password);
        if(userServices.checkName(user)){
            error="该用户已存在";
            modelMap.addAttribute("error",error);
            return "views/login/register";
        }
        userServices.saveUser(user);
        return "redirect:/sbiademo/login";
    }
    //获取登录页面
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String getLogin(){
        return "views/login/login";
    }
    //进行登录；登录不走过滤器
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        ModelMap modelMap, ServletRequest servletRequest,
                        ModelAndView modelAndView){
        String error = null;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (AuthenticationException e) {
            //其他错误，比如锁定，如果想单独处理请单独catch处理
            error = "其他错误：" + e.getMessage();
        }
        if(error != null) {//出错了，返回登录页面
            modelAndView.addObject("error",error);
            modelAndView.setViewName("views/login/login");
            return modelAndView;
        } else {//登录成功
            modelAndView.addObject("error","登陆成功");
        }
        //重定向到登录前请求地址；重定向时会生成会话，在其属性中保存当前的请求
        String successUrl="";
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(servletRequest);
        if (savedRequest != null && savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)) {
            successUrl = savedRequest.getRequestUrl();
        }
        if(!successUrl.equals("")){
            //System.out.println(successUrl);
            modelAndView.setView(new RedirectView(successUrl,false));
            return modelAndView;
        }
        modelAndView.setViewName("views/login/login");
        return modelAndView;
    }
    @RequestMapping(value = "unauthor",method = RequestMethod.GET)
    public String getUnauthor(){
        return "views/login/unauthorized";
    }
    //用户登出
    @RequestMapping(value = "loginOut",method = RequestMethod.GET)
    public String getLoginOut(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            subject.logout();
        }
        return "redirect:/sbiademo/index";
    }
}
