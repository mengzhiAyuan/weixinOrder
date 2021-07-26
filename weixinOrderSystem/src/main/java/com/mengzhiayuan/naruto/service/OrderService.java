package com.mengzhiayuan.naruto.service;


import com.github.pagehelper.PageInfo;
import com.mengzhiayuan.naruto.dto.OrderDTO;

public interface OrderService {
    /*创建订单*/
    OrderDTO create(OrderDTO orderDTO);

    /*查询单个订单*/
    OrderDTO findOne(String OrderId);

    /*查询订单列表*/
    PageInfo<OrderDTO> findList(String buyerOpenid, int page, int pageSize);

    /*取消订单*/
    OrderDTO cancel(OrderDTO orderDTO);

    /*完结订单*/
    OrderDTO finish(OrderDTO orderDTO);

    /*支付订单*/
    OrderDTO paid(OrderDTO orderDTO);

    /*查询订单列表*/
    PageInfo<OrderDTO> findList(int page, int pageSize);

}
