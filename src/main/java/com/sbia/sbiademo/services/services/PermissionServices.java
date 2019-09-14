package com.sbia.sbiademo.services.services;

import com.sbia.sbiademo.model.Permission;

import java.util.List;

public interface PermissionServices {
    List<Permission> getAllPermissions();
    int savePermission(Permission permission);
    int updateByPrimaryKey(Permission permission);
    int deleteByPrimaryKey(Permission permission);
}
