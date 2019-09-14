package com.sbia.sbiademo.model;

/**
 *   u_role表
 */
public class Role {
    /**
     *   id
     */
    private Long id;

    /**
     *   角色名称
     *   name
     */
    private String name;

    /**
     *   角色类型
     *   type
     */
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}