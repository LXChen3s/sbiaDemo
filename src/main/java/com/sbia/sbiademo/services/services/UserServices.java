package com.sbia.sbiademo.services.services;

import com.sbia.sbiademo.model.User;
import com.sbia.sbiademo.model.UserWrapper;

import java.util.List;

public interface UserServices {
    User getUserByUsername(String username);
    int saveUser(User user);
    List<Integer> getPermisssionByUsername(String name);
    boolean checkName(User user);

    List<UserWrapper> selectAll2();

    void deleteRoleForUser(Long id);

    void insertRoleForUser( Long uid, Long rid);
}
