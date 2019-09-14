package com.sbia.sbiademo.util;

import javax.servlet.http.HttpServletRequest;

public class SiteAdress {
    /* 获取服务器协议、地址、端口。如http://localhost:8080/ */
    public static String getSiteHost(HttpServletRequest httpServletRequest){
        return httpServletRequest.getScheme()+"://"+
                httpServletRequest.getServerName()+":"+
                httpServletRequest.getServerPort()+"/"+
                httpServletRequest.getContextPath() +"/";
    }
}
