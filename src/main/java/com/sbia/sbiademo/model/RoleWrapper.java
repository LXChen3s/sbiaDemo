package com.sbia.sbiademo.model;

import java.util.List;

public class RoleWrapper extends Role {
    private List<Permission> pList;

    public List<Permission> getpList() {
        return pList;
    }

    public void setpList(List<Permission> pList) {
        this.pList = pList;
    }
}
