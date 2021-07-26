package com.mengzhiayuan.naruto.controller;


import com.github.pagehelper.PageInfo;
import com.mengzhiayuan.naruto.VO.ResultVO;
import com.mengzhiayuan.naruto.dto.OrderDTO;
import com.mengzhiayuan.naruto.enums.ResultEnum;
import com.mengzhiayuan.naruto.exception.SellException;
import com.mengzhiayuan.naruto.form.OrderForm;
import com.mengzhiayuan.naruto.service.BuyerService;
import com.mengzhiayuan.naruto.service.OrderService;
import com.mengzhiayuan.naruto.utils.OrderFormShiftOrderDTO;
import com.mengzhiayuan.naruto.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：mengzhiayuan
 * @description：
 *
 * //TODO @Valid
 * 用于验证注解是否符合要求，直接加在变量user之前，在变量中添加验证信息的要求，当不符合要求时就会在方法中返回message 的错误提示信息
 * @Valid和BindingResult配套使用，@Valid用在参数前，BindingResult作为校验结果绑定返回。
 * bindingResult.hasErrors()判断是否校验通过，校验未通过，bindingResult.getFieldError().getDefaultMessage()
 * 获取在TestEntity的属性设置的自定义message，如果没有设置，则返回默认值"javax.validation.constraints.XXX.message"。
 * @date ：2021/7/22 15:37
 */

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("[创建订单] 参数不正确， orderForm = {} ",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderFormShiftOrderDTO.convert(orderForm);
        if(orderDTO.getOrderDetailList().isEmpty()){
            log.error("[创建订单] 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);

        Map<String,String> map=new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVOUtils.success(map);
    }


    //订单列表    查询列表的时候不需要查详情
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,//为了健壮性，最好给一个默认值
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(openid==null){
            log.error("[查询订单列表 openid 为空]");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageInfo<OrderDTO> orderDTOPageInfo = orderService.findList(openid, page, size);
        List<OrderDTO> orderDTOList = orderDTOPageInfo.getList();
        return ResultVOUtils.success(orderDTOList); //返回最外层视图
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> datail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){

        //TODO 不安全的做法,改进 任何人都可以通过一个openid进行查询详情
//        OrderDTO orderDTO = orderService.findOne(orderId);

        OrderDTO orderOne = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtils.success(orderOne);
    }


    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){

        //TODO 不安全的做法,改进
//        OrderDTO orderDTO =orderService.findOne(orderId);
//        orderService.cancel(orderDTO);

        buyerService.cancelOrder(openid, orderId);
        return ResultVOUtils.success();
    }
}
