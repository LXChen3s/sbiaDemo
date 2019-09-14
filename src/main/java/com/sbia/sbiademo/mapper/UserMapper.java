package com.sbia.sbiademo.mapper;

import com.sbia.sbiademo.model.User;
import com.sbia.sbiademo.model.UserWrapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByUsername(@Param("name") String name);

    List<Integer> selectPermisssionByUsername(@Param("name") String name);

    Integer selectIdByUsername(@Param("name") String name);

    List<UserWrapper> selectAll2();

    void deleteRoleForUser(@Param("id") Long id);

    void insertRoleForUser(@Param("uid") Long uid,@Param("rid") Long rid);
}