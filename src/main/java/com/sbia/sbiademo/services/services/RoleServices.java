package com.sbia.sbiademo.services.services;

import com.sbia.sbiademo.model.Role;
import com.sbia.sbiademo.model.RoleWrapper;

import java.util.List;

public interface RoleServices {
    int insert(Role record);
    void insertPermissionForRole(Long rid,Long pid);
    List<RoleWrapper> selectAll();
    int updateByPrimaryKey(Role record);

    void deletePermissionForRole(Long id);

    int deleteByPrimaryKey(Long id);
}
