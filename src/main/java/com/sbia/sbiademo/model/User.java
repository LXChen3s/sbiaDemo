package com.sbia.sbiademo.model;

import java.util.Date;

/**
 *   tb_user表
 */
public class User {
    /**
     *   用户id
     *   id
     */
    private Integer id;

    /**
     *   用户名
     *   name
     */
    private String name;

    /**
     *   用户密码
     *   password
     */
    private String password;

    /**
     *   用户邮箱
     *   email
     */
    private String email;

    /**
     *   用户创建时间
     *   create_time
     */
    private Date createTime;

    /**
     *   用户最后登录时间
     *   last_login_time
     */
    private Date lastLoginTime;

    /**
     *   用户状态；1:有效，0:禁止登录
     *   status
     */
    private Long status;

    /**
     *   用户昵称
     *   nickname
     */
    private String nickname;

    /**
     *   密码盐
     *   salt
     */
    private String salt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}