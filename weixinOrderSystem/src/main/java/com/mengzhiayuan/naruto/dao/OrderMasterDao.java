package com.mengzhiayuan.naruto.dao;

import com.mengzhiayuan.naruto.entity.OrderMaster;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMasterDao {

    List<OrderMaster> findByBuyerOpenid(String buyerOpenid);

    void save(OrderMaster orderMaster);

    OrderMaster findOne(String orderId);

    int updateStatus(OrderMaster orderMaster);

    //卖家查询订单
    List<OrderMaster> findAll();
}
