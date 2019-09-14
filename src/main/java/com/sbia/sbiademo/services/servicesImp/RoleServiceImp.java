package com.sbia.sbiademo.services.servicesImp;

import com.sbia.sbiademo.mapper.RoleMapper;
import com.sbia.sbiademo.model.Role;
import com.sbia.sbiademo.model.RoleWrapper;
import com.sbia.sbiademo.services.services.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleServices {
    @Autowired
    RoleMapper roleMapper;
    //插入角色
    @Override
    public int insert(Role record) {
        return roleMapper.insert(record);
    }
    //插入角色对应权限
    @Override
    public void insertPermissionForRole(Long rid, Long pid) {
        roleMapper.insertPermissionForRole(rid,pid);
    }
    //获取所有的角色，附带权限集合
    @Override
    public List<RoleWrapper> selectAll() {
        List<RoleWrapper> rList=roleMapper.selectAll();
        for(RoleWrapper roleWrapper:rList){
            roleWrapper.setpList(roleMapper.selectPermissionForRole(roleWrapper.getId()));
        }
        return rList;
    }
    //更新角色
    @Override
    public int updateByPrimaryKey(Role record) {
        return roleMapper.updateByPrimaryKey(record);
    }
    //根据角色id删除权限关系
    @Override
    public void deletePermissionForRole(Long id) {
        roleMapper.deletePermissionForRole(id);
    }
    //删除角色
    @Override
    public int deleteByPrimaryKey(Long id) {
        int i=roleMapper.deleteByPrimaryKey(id);
        deletePermissionForRole(id);
        return i;
    }

}
