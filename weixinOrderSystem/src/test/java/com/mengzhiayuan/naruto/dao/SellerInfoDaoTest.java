package com.mengzhiayuan.naruto.dao;

import com.mengzhiayuan.naruto.entity.SellerInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class SellerInfoDaoTest {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Test
    public void findByOpenid(){
        SellerInfo byOpenid = sellerInfoDao.findByOpenid("1");
        System.out.println(byOpenid);
    }
}