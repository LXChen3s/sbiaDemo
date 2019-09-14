package com.sbia.sbiademo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sbia.sbiademo.model.Role;
import com.sbia.sbiademo.model.RoleWrapper;
import com.sbia.sbiademo.services.services.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sbiademo/admin/")
public class RoleController {
    @Autowired
    RoleServices roleServices;

    //获取所有角色
    @RequestMapping(value = "getAllRole",method = RequestMethod.GET)
    @ResponseBody
    public String getAllRole(){
        //获取所有的角色
        List<RoleWrapper> roles=roleServices.selectAll();
        String jsonRoles=JSON.toJSONString(roles);
        return "{\"roles\":"+jsonRoles+"}";
    }

    //添加角色，按理说应该调用存储过程执行角色及相关权限的添加
    @RequestMapping(value = "saveRole",method = RequestMethod.POST)
    @ResponseBody
    public String saveRole(@RequestBody String jsonString){
        //根据字符串获取json对象，从json对象中获取json数组
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        String rName=jsonObject.getString("rName");
        Role role=new Role();
        role.setName(rName);
        //添加角色
        roleServices.insert(role);
        //获取角色id
        Long roleId=role.getId();
        JSONArray jsonArray = jsonObject.getJSONArray("pArray");
        for(int i=0;i<jsonArray.size();i++){
            //获取添加角色的权限id
            Integer pid = jsonArray.getInteger(i);
            roleServices.insertPermissionForRole(roleId,pid.longValue());
        }
        //获取所有的角色
        List<RoleWrapper> roles=roleServices.selectAll();
        String jsonRoles=JSON.toJSONString(roles);
        return "{\"roles\":"+jsonRoles+"}";
    }

    //更新角色，按理说应该调用存储过程执行角色及相关权限的添加
    @RequestMapping(value = "updateRole",method = RequestMethod.POST)
    @ResponseBody
    public String updateRole(@RequestBody String jsonString){
        //根据字符串获取json对象，从json对象中获取json数组
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Long id=jsonObject.getLong("id");
        String rName=jsonObject.getString("name");
        Role role=new Role();
        role.setId(id);
        role.setName(rName);
        roleServices.updateByPrimaryKey(role);
        JSONArray jsonArray = jsonObject.getJSONArray("pArray");
        roleServices.deletePermissionForRole(role.getId());
        for(int i=0;i<jsonArray.size();i++){
            //获取添加角色的权限id
            Integer pid = jsonArray.getInteger(i);
            roleServices.insertPermissionForRole(role.getId(),pid.longValue());
        }
        //获取所有的角色
        List<RoleWrapper> roles=roleServices.selectAll();
        String jsonRoles=JSON.toJSONString(roles);
        return "{\"roles\":"+jsonRoles+"}";
    }

    //删除角色，按理说应该调用存储过程执行角色及相关权限的添加
    @RequestMapping(value = "deleteRole",method = RequestMethod.POST)
    @ResponseBody
    public String deleteRole(@RequestBody Map<String,String> map){
        Long rid= (long) Integer.parseInt(map.get("id"));
        roleServices.deleteByPrimaryKey(rid);
        //获取所有的角色
        List<RoleWrapper> roles=roleServices.selectAll();
        String jsonRoles=JSON.toJSONString(roles);
        return "{\"roles\":"+jsonRoles+"}";
    }

}
