package com.mengzhiayuan.naruto.service.Impl;

import com.mengzhiayuan.naruto.entity.ProductInfo;
import com.mengzhiayuan.naruto.enums.ProductStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    void findOne() {
        System.out.println(productService.findOne("123"));
    }

    @Test
    void findUpAll() {
        System.out.println(productService.findUpAll());
    }

    @Test
    void findAll() {
        System.out.println(productService.findAll(0,1));
    }

    @Test
    void save() {
        productService.save(new ProductInfo("1","naruto",new BigDecimal(1.0),9999,"naruto"
                        ,null, ProductStatusEnum.UP.getCode(),1,new Date(),new Date()));
    }
}