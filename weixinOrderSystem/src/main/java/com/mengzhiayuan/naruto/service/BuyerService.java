package com.mengzhiayuan.naruto.service;


import com.mengzhiayuan.naruto.dto.OrderDTO;

//买家订单 ------安全处理
public interface BuyerService {

    OrderDTO findOrderOne(String openid,String orderId);

    OrderDTO cancelOrder(String openid,String orderId);
}
