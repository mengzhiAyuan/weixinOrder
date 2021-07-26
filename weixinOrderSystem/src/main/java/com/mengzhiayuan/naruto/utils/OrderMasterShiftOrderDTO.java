package com.mengzhiayuan.naruto.utils;

import com.mengzhiayuan.naruto.dto.OrderDTO;
import com.mengzhiayuan.naruto.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/21 17:53
 */

//转换器
public class OrderMasterShiftOrderDTO {

    public static OrderDTO convert(OrderMaster orderMaster){

        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){

        List<OrderDTO> resultList=new ArrayList<>();
        for(OrderMaster e : orderMasterList){
            OrderDTO orderDTO=new OrderDTO();
            BeanUtils.copyProperties(e,orderDTO);
            resultList.add(orderDTO);
        }

        return resultList;
    }
}
