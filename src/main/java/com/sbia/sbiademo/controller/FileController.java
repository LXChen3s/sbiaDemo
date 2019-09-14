package com.sbia.sbiademo.controller;

import com.sbia.sbiademo.services.services.FileServices;
import com.sbia.sbiademo.util.SiteAdress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/sbiademo/admin/")
public class FileController {
    @Autowired
    FileServices fileServices;

    @RequestMapping(value = "uploadImage",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public String uploadImage(@RequestPart("upload") MultipartFile multipartFile, HttpServletRequest httpServletRequest){
        String url=SiteAdress.getSiteHost(httpServletRequest)+
                fileServices.saveFile(multipartFile);
        return "{\"uploaded\":\"success\",\"url\":\""+url+"\"}";
    }
}
