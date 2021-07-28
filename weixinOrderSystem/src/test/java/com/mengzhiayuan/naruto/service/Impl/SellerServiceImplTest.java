package com.mengzhiayuan.naruto.service.Impl;

import com.mengzhiayuan.naruto.entity.SellerInfo;
import com.mengzhiayuan.naruto.service.SellerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class SellerServiceImplTest {

    @Autowired
    private SellerService sellerService;

    @Test
    public void findSellerInfoByOpenid() {

        System.out.println(sellerService.findSellerInfoByOpenid("1"));
    }

}