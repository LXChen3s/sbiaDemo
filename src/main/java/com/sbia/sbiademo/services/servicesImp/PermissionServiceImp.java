package com.sbia.sbiademo.services.servicesImp;

import com.sbia.sbiademo.mapper.PermissionMapper;
import com.sbia.sbiademo.model.Permission;
import com.sbia.sbiademo.services.services.PermissionServices;
import com.sbia.sbiademo.util.shiro.ShiroFilterChainManager;
import com.sbia.sbiademo.util.shiro.UrlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImp implements PermissionServices {
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    private ShiroFilterChainManager shiroFilerChainManager;

    /*
     * 容器启动时初始化shiro拦截器连；添加、更新权限也要更新shiro拦截器连
     */
    @PostConstruct
    public void initFilterChain() {
        List<Permission> permissions=getAllPermissions();
        List<UrlFilter> urlFilters=new ArrayList<>();
        for(Permission permission:permissions){
            UrlFilter urlFilter=new UrlFilter();
            urlFilter.setUrl(permission.getUrl());
            urlFilter.setPermissions(permission.getId().toString());
            urlFilters.add(urlFilter);
        }
        shiroFilerChainManager.initFilterChains(urlFilters);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionMapper.selectAll();
    }
    //添加权限
    @Override
    public int savePermission(Permission permission) {
        int i=permissionMapper.insert(permission);
        initFilterChain();
        return i;
    }
    //更新权限
    @Override
    public int updateByPrimaryKey(Permission permission) {
        int i=permissionMapper.updateByPrimaryKey(permission);
        initFilterChain();
        return i;
    }

    @Override
    public int deleteByPrimaryKey(Permission permission) {
        int i=permissionMapper.deleteByPrimaryKey(permission.getId());
        initFilterChain();
        return i;
    }
}
