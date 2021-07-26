package com.mengzhiayuan.naruto.service;

import com.mengzhiayuan.naruto.entity.ProductCategory;

import java.util.List;

//买家类目
public interface CategoryService {
    //通过类目编号找类目
    ProductCategory findOne(Integer categoryId);
    //找到所有的类目
    List<ProductCategory> findAll();
    //通过多个类目编号找多个类目
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    //保存类目
    void save(ProductCategory productCategory);
}
