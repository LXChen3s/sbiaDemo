package com.sbia.sbiademo.model;

import java.util.List;

public class UserWrapper extends User {
    private List<Role> rList;

    public List<Role> getrList() {
        return rList;
    }

    public void setrList(List<Role> rList) {
        this.rList = rList;
    }
}
