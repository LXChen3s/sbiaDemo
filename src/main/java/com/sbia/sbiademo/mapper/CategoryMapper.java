package com.sbia.sbiademo.mapper;

import com.sbia.sbiademo.model.Category;
import com.sbia.sbiademo.model.wrapper.CategoryWrapper;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    Category selectByPrimaryKey(Integer id);

    List<Category> selectAll();

    int updateByPrimaryKey(Category record);

    List<CategoryWrapper> selectWithCount();
}