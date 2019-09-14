package com.sbia.sbiademo.controller;

import com.alibaba.fastjson.JSON;
import com.sbia.sbiademo.model.Permission;
import com.sbia.sbiademo.services.services.PermissionServices;
import com.sbia.sbiademo.services.services.RoleServices;
import com.sbia.sbiademo.services.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sbiademo/admin/")
public class PermissionController {
    @Autowired
    PermissionServices permissionServices;
    @Autowired
    RoleServices roleServices;
    @Autowired
    UserServices userServices;

    @RequestMapping(value = "getPermissionIndex",method = RequestMethod.GET)
    public String getPermissionIndex(ModelMap modelMap){
        //获取所有权限
        modelMap.addAttribute("permissions",permissionServices.getAllPermissions());
        modelMap.addAttribute("roles",roleServices.selectAll());
        modelMap.addAttribute("users",userServices.selectAll2());
        return "views/admin/permission/permissionIndex";
    }

    @RequestMapping(value = "getAllPermission",method = RequestMethod.GET)
    @ResponseBody
    public String getAllPermission(){
        //获取所有权限
        String permissions= JSON.toJSONString(permissionServices.getAllPermissions());
        return "{\"state\":\"success\",\"permissions\":"+permissions+"}";
    }
    @RequestMapping(value = "getPermissionPage",method = RequestMethod.GET)
    public String getPermissionPage(){
        return "views/admin/permission/changePermission";
    }
    //添加权限,两项属性均不能为空
    @RequestMapping(value = "changePermission",method = RequestMethod.POST)
    @ResponseBody
    public String changePermission(@RequestBody Map<String,String> map,ModelMap modelMap){
        Permission permission=new Permission();
        permission.setUrl(map.get("pUrl"));
        permission.setName(map.get("pName"));
        permissionServices.savePermission(permission);
        String permissions= JSON.toJSONString(permissionServices.getAllPermissions());
        return "{\"state\":\"success\",\"permissions\":"+permissions+"}";
    }
    //更新权限
    @RequestMapping(value = "updatePermission",method = RequestMethod.POST)
    @ResponseBody
    public String updatePermission(@RequestBody Permission permission,ModelMap modelMap){
        permissionServices.updateByPrimaryKey(permission);
        String permissions= JSON.toJSONString(permissionServices.getAllPermissions());
        return "{\"state\":\"success\",\"permissions\":"+permissions+"}";
    }
    //删除权限
    @RequestMapping(value = "deletePermission",method = RequestMethod.POST)
    @ResponseBody
    public String deletePermission(@RequestBody Permission permission,ModelMap modelMap){
        permissionServices.deleteByPrimaryKey(permission);
        String permissions= JSON.toJSONString(permissionServices.getAllPermissions());
        return "{\"state\":\"success\",\"permissions\":"+permissions+"}";
    }
}
