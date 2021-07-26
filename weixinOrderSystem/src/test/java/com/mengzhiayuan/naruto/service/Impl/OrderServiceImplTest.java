package com.mengzhiayuan.naruto.service.Impl;


import com.github.pagehelper.PageInfo;
import com.mengzhiayuan.naruto.dto.OrderDTO;
import com.mengzhiayuan.naruto.entity.OrderDetail;
import com.mengzhiayuan.naruto.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    private final String openid="100011";

    private final String orderId="1626851348151676236";

    private final String orderId2="16268517305684024179";

    @Test
    void create() throws Exception{

        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("小鸣人");
        orderDTO.setBuyerAddress("------");
        orderDTO.setBuyerPhone("1242314");
        orderDTO.setBuyerOpenid(openid);

        //购物车
        List<OrderDetail> orderDetailList =new ArrayList<>();
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setProductId("123");
        orderDetail.setProductQuantity(3);
        orderDetailList.add(orderDetail);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("[创建订单] result = {}",result);
    }

    @Test
    void findOne() {
        OrderDTO result = orderService.findOne(orderId);
        log.info("查询单个订单 result = {} ",result);
    }

    @Test
    void findList() {
        PageInfo<OrderDTO> result = orderService.findList(openid, 0, 2);

    }

    @Test
    void cancel() throws Exception{
        OrderDTO orderDTO =orderService.findOne(orderId);
        OrderDTO result = orderService.cancel(orderDTO);
        log.info("[取消订单]+  result {}",result);
    }

    @Test
    void finish(){
        OrderDTO orderDTO =orderService.findOne(orderId2);
        OrderDTO result = orderService.finish(orderDTO);
        log.info("[完成订单]+  result {}",result);
    }

    @Test
    void paid() {
        OrderDTO orderDTO =orderService.findOne("1626851348151676236");
        OrderDTO result = orderService.paid(orderDTO);
        log.info("[支付订单]+  result {}",result);
    }

    @Test
    void List(){
        PageInfo<OrderDTO> result = orderService.findList( 0, 2);
        List<OrderDTO> list = result.getList();
        for(OrderDTO e : list){
            System.out.println(e);
        }
    }
}