package com.sbia.sbiademo.mapper;

import com.sbia.sbiademo.model.Permission;
import com.sbia.sbiademo.model.Role;
import com.sbia.sbiademo.model.RoleWrapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<RoleWrapper> selectAll();

    int updateByPrimaryKey(Role record);

    void insertPermissionForRole(@Param("rid")Long rid,@Param("pid")Long pid);

    List<Permission> selectPermissionForRole(@Param("id") Long id);

    void deletePermissionForRole(@Param("id")Long id);
}