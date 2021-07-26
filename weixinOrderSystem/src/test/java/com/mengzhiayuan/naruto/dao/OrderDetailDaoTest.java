package com.mengzhiayuan.naruto.dao;

import com.mengzhiayuan.naruto.entity.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    void findByOrderId() {
        System.out.println(orderDetailDao.findByOrderId(""));
    }

    @Test
    void save() {
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("3155341");
        orderDetail.setOrderId("11212121");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductId("12132");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(2.2));
        orderDetail.setProductQuantity(3);

        orderDetailDao.save(orderDetail);
    }
}