package com.mengzhiayuan.naruto.dao;


import com.mengzhiayuan.naruto.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderDetailDao {

    List<OrderDetail> findByOrderId(String orderId);

    void save(OrderDetail orderDetail);
}
