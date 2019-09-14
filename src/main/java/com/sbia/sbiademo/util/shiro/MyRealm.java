package com.sbia.sbiademo.util.shiro;
import com.sbia.sbiademo.model.User;
import com.sbia.sbiademo.services.services.UserServices;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    UserServices userServices;
    /*
     * 进行授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //从数据库获取所有角色、根据角色添加权限信息
        //authorizationInfo.addRole("role1");
        //authorizationInfo.addRole("role2");
        //authorizationInfo.addObjectPermission(new WildcardPermission("user1:*"));
        //authorizationInfo.addStringPermission("user2:*");
        List<Integer> perminssions=userServices.getPermisssionByUsername(principals.getPrimaryPrincipal().toString());
        for(Integer i:perminssions){
            authorizationInfo.addStringPermission(i.toString());
        }
        return authorizationInfo;
    }
    /*
     * 进行认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取表单提交的用户名，这是唯一的
        String username = (String)token.getPrincipal();  //得到用户名 (邮箱、电话、账号)
        //String password = new String((char[])token.getCredentials()); //得到密码
        //根据表单提供的用户名获取user
        User user=userServices.getUserByUsername(username);
        //根据用户名找不到数据抛出未知用户异常
        if(user==null){
            throw new UnknownAccountException();
        }
        //获取加密的密码、盐
        String password=user.getPassword();
        String salt=user.getSalt();
        //数据库中取：
        //String password2 = "bf0d2a8b27b67751cf54df736c6ac494"; //加密后的密码
        //String salt2 = "b8d718ee81b92138caa19017425158ef"; //盐

        //SimpleAuthenticationInfo ai =
        //        new SimpleAuthenticationInfo(username, password2, getName());
        SimpleAuthenticationInfo ai =
                new SimpleAuthenticationInfo(username, password, getName());
        ai.setCredentialsSalt(ByteSource.Util.bytes(salt));
        //ai.setCredentialsSalt(ByteSource.Util.bytes(username+salt2)); //设置盐是用户名+随机数

        //返回从数据库查询的加密密码、盐,构建从数据库中查询出的加密认证信息
        //CredentialsMatcher会匹配用户输入的token的凭证（AuthenticationToken未加密）与系统提供的凭证（AuthenticationInfo已加密）
        return ai;
        //return new SimpleAuthenticationInfo("wu",passwordService.encryptPassword("123"),getName());
    }
}
