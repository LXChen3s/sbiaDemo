package com.sbia.sbiademo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sbia.sbiademo.model.UserWrapper;
import com.sbia.sbiademo.services.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sbiademo/admin/")
public class UserController {
    @Autowired
    UserServices userServices;

    //更新用户角色，按理说应该调用存储过程执行角色及相关权限的添加
    @RequestMapping(value = "updateUserRole",method = RequestMethod.POST)
    @ResponseBody
    public String updateRole(@RequestBody String jsonString){
        //根据字符串获取json对象，从json对象中获取json数组
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Long id=jsonObject.getLong("id");
        userServices.deleteRoleForUser(id);
        JSONArray jsonArray = jsonObject.getJSONArray("rArray");
        for(int i=0;i<jsonArray.size();i++){
            //获取添加角色的权限id
            Integer rid = jsonArray.getInteger(i);
            userServices.insertRoleForUser(id,rid.longValue());
        }
        //获取所有的角色
        List<UserWrapper> users=userServices.selectAll2();
        String jsonUsers=JSON.toJSONString(users);
        return "{\"users\":"+jsonUsers+"}";
    }
}
