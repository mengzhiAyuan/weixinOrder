package com.mengzhiayuan.naruto.service.Impl;

import com.mengzhiayuan.naruto.entity.ProductCategory;
import com.mengzhiayuan.naruto.service.Impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/19 13:42
 */

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        System.out.println(categoryService.findOne(1));
    }

    @Test
    public void findAll() {
        System.out.println(categoryService.findAll());
    }

    @Test
    public void findByCategoryTypeIn() {
        System.out.println(categoryService.findByCategoryTypeIn(new ArrayList<>(Arrays.asList(1,2))));
    }

    @Test
    public void save() {
        categoryService.save(new ProductCategory(3,"naruto",10,new Date(),new Date()));
    }
}
