package com.sbia.sbiademo.services.services;

import com.sbia.sbiademo.model.wrapper.CategoryWrapper;

import java.util.List;

public interface CategoryServices {
    List<CategoryWrapper> selectWithCount();
}
