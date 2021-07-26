package com.mengzhiayuan.naruto.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mengzhiayuan.naruto.dto.OrderDTO;
import com.mengzhiayuan.naruto.entity.OrderDetail;
import com.mengzhiayuan.naruto.enums.ResultEnum;
import com.mengzhiayuan.naruto.exception.SellException;
import com.mengzhiayuan.naruto.form.OrderForm;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/22 16:26
 */

@Slf4j
//将前端表单数据转换为 OrderDTO
public class OrderFormShiftOrderDTO {
    //前端购物车只传入 id 和数量 ，其他信息由数据库提供 ！！！！

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();

        OrderDTO orderDTO =new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList =new ArrayList<>();
        try {
            orderDetailList=gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        }catch (Exception e){
            log.error("[对象转换] 错误， String ={} ",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
