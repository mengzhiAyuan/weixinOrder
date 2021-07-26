package com.mengzhiayuan.naruto.dao;

import com.mengzhiayuan.naruto.entity.OrderMaster;
import com.mengzhiayuan.naruto.enums.OrderStatusEnum;
import com.mengzhiayuan.naruto.enums.PayStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    private final String openid="1010001";

    @Test
    void findByBuyerOpenid() {
        System.out.println(orderMasterDao.findByBuyerOpenid(openid));
    }

    @Test
    void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("555");
        orderMaster.setBuyerName("mengzhiayuan");
        orderMaster.setBuyerPhone("9999999999");
        orderMaster.setBuyerAddress("luowei");
        orderMaster.setBuyerOpenid(openid);
        orderMaster.setOrderAmount(new BigDecimal(23.0));
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(orderMaster);
    }
    @Test
    void updataSatus(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1626851348151676236");
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        orderMasterDao.updateStatus(orderMaster);
    }

}