package com.mengzhiayuan.naruto.dao;

import com.mengzhiayuan.naruto.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CategoryDao {
    //通过类目编号找类目
    ProductCategory findOne(Integer categoryId);
    //找到所有的类目
    List<ProductCategory> findAll();
    //通过多个类目编号找多个类目
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    //保存类目
    void save(ProductCategory productCategory);
}
