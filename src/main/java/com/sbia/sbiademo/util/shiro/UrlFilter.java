package com.sbia.sbiademo.util.shiro;

public class UrlFilter {
    private Long id;
    private String name; //url名称/描述
    private String url; //地址(唯一)
    /*
     * /role=authc,roles[admin]
     * /permission=authc,perms["user:create"]
     */
    private String roles; //所需要的角色所有参数，以逗号分隔，可省略
    private String permissions; //所需要的权限所有参数，以逗号分隔，可省略

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
