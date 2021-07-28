package com.mengzhiayuan.naruto.service;

import com.mengzhiayuan.naruto.dto.OrderDTO;

/**
 * 推送消息
 * Created by mengzhiayuan
 * 2021-07-28 22:08
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}