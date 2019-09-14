package com.sbia.sbiademo.services.servicesImp;

import com.sbia.sbiademo.mapper.UserMapper;
import com.sbia.sbiademo.model.User;
import com.sbia.sbiademo.model.UserWrapper;
import com.sbia.sbiademo.services.services.UserServices;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserServices {
    @Autowired
    UserMapper userMapper;
    //根据用户名获取user、主要获取密码
    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
    //保存user、注册用户；用户名唯一应校验
    @Override
    public int saveUser(User user) {
        //加密算法名称
        String algorithmName = "md5";
        //盐
        String salt1 = user.getName();
        //SecureRandomNumberGenerator用于生成一个随机数
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        user.setSalt(salt1+salt2);
        //哈希迭代次数
        int hashIterations = 2;
        SimpleHash hash = new SimpleHash(algorithmName, user.getPassword(), salt1+salt2 , hashIterations);
        String encodedPassword = hash.toHex();
        user.setPassword(encodedPassword);
        return userMapper.insert(user);
    }
    //检测用户名是否已存在；不存在，flase;存在，true；
    @Override
    public boolean checkName(User user) {
        Integer i=userMapper.selectIdByUsername(user.getName());
        return i == 1;
    }
    //获取所有用户带角色信息
    @Override
    public List<UserWrapper> selectAll2() {
        return userMapper.selectAll2();
    }

    @Override
    public void deleteRoleForUser(Long id) {
        userMapper.deleteRoleForUser(id);
    }

    @Override
    public void insertRoleForUser(Long uid, Long rid) {
        userMapper.insertRoleForUser(uid,rid);
    }

    /*
     * 根据用户名获取所有权限
     */
    @Override
    public List<Integer> getPermisssionByUsername(String name) {
        return userMapper.selectPermisssionByUsername(name);
    }
}
