package com.sbia.sbiademo.model.wrapper;

import com.sbia.sbiademo.model.Category;

public class CategoryWrapper extends Category {
    //各类别文章数量
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
