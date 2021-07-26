package com.mengzhiayuan.naruto.service.Impl;

import com.mengzhiayuan.naruto.dao.CategoryDao;
import com.mengzhiayuan.naruto.entity.ProductCategory;
import com.mengzhiayuan.naruto.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/19 13:32
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return categoryDao.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return categoryDao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public void save(ProductCategory productCategory) {
        categoryDao.save(productCategory);
    }
}
