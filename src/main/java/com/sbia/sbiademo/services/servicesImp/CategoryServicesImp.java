package com.sbia.sbiademo.services.servicesImp;

import com.sbia.sbiademo.mapper.CategoryMapper;
import com.sbia.sbiademo.model.wrapper.CategoryWrapper;
import com.sbia.sbiademo.services.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServicesImp implements CategoryServices {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryWrapper> selectWithCount() {
        return categoryMapper.selectWithCount();
    }
}
