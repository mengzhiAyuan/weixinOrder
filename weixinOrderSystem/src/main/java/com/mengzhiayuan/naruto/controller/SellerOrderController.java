package com.mengzhiayuan.naruto.controller;

import com.github.pagehelper.PageInfo;
import com.mengzhiayuan.naruto.dto.OrderDTO;
import com.mengzhiayuan.naruto.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 *
 * 卖家端订单
 * @date ：2021/7/25 16:44
 */

@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     *订单列表
     * @param page 第几页 从一页开始
     * @param size 一页有多少数据
     */

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){

        PageInfo<OrderDTO> orderDTOPage = orderService.findList(page - 1, size);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("order/list",map);
    }
}
