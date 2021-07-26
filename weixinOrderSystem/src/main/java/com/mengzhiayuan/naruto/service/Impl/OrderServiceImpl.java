package com.mengzhiayuan.naruto.service.Impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengzhiayuan.naruto.dao.OrderDetailDao;
import com.mengzhiayuan.naruto.dao.OrderMasterDao;
import com.mengzhiayuan.naruto.dto.CartDTO;
import com.mengzhiayuan.naruto.dto.OrderDTO;
import com.mengzhiayuan.naruto.entity.OrderDetail;
import com.mengzhiayuan.naruto.entity.OrderMaster;
import com.mengzhiayuan.naruto.entity.ProductInfo;
import com.mengzhiayuan.naruto.enums.OrderStatusEnum;
import com.mengzhiayuan.naruto.enums.PayStatusEnum;
import com.mengzhiayuan.naruto.enums.ResultEnum;
import com.mengzhiayuan.naruto.exception.SellException;
import com.mengzhiayuan.naruto.service.OrderService;
import com.mengzhiayuan.naruto.service.PayService;
import com.mengzhiayuan.naruto.service.ProductService;
import com.mengzhiayuan.naruto.utils.KeyUtil;
import com.mengzhiayuan.naruto.utils.OrderMasterShiftOrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/20 17:59
 */

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private PayService payService;

    //创建订单前检查是否有库存，从数据库里拿单价
    //前端不会传入所有数据 ，这里订单只会传入订单id 和 订单数量

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId= KeyUtil.getUniqueKey();//生成订单的唯一性id
        BigDecimal orderAmount= new BigDecimal(0);

        List<CartDTO> cartDTOList=new ArrayList<>();

        //1.查询商品数量（数量，价格）
        for(OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            //2.计算订单总价 应该从数据库中拿商品单价,因为前端只会传入 订单id 和数量
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库
            orderDetail.setOrderId(orderId);//orederId 从下单就有
            orderDetail.setDetailId(KeyUtil.getUniqueKey());

            //前端只传入商品id 和数量，orderDetail 其他属性我们需要从productInfo中找
//            如果productInfo和orderDetail间存在名称不相同的属性，则BeanUtils不对这些属性进行处理，需要手动处理
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailDao.save(orderDetail);

            CartDTO cartDTO=new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);

        }


        //3.写入订单数据库（orderMaster , orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId); //注意顺序 ！！！
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(orderMaster);

        //4.扣库存  注意！！！这里有线程安全问题
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    //通过订单id  查找订单  一个master 和多个 订单详情页
    @Override
    public OrderDTO findOne(String orderId) {

        OrderMaster orderMaster = orderMasterDao.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if(orderDetailList.isEmpty()){
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public PageInfo<OrderDTO> findList(String buyerOpenid, int page, int pageSize) {

        //设置分页
        PageHelper.startPage(page,pageSize);
        List<OrderMaster> orderMasterList = orderMasterDao.findByBuyerOpenid(buyerOpenid);
        List<OrderDTO> orderDTOList = OrderMasterShiftOrderDTO.convert(orderMasterList);
        return new PageInfo<>(orderDTOList);

    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {

        OrderMaster orderMaster =new OrderMaster();

        //判断订单状态 支付之前撤销订单
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[取消订单] 订单状态不正确， orderId = {}，orderStatus = {} ",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);

        int i = orderMasterDao.updateStatus(orderMaster);
        if(i==0){
            log.error("[取消订单  更新失败]，orderMaster={} " ,orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返回库存
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        if(orderDetailList.isEmpty()){
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        List<CartDTO> cartDTOList=new ArrayList<>();
        for(OrderDetail e : orderDetailList){
            cartDTOList.add(new CartDTO(e.getProductId(),e.getProductQuantity()));
        }
        productService.increaseStock(cartDTOList);

        //如果已经支付，需要退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            payService.refund(orderDTO);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[完结订单] 订单状态不正确 , orderId= {},orderStatus ={}",orderDTO.getOrderId(),orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster= new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        int i = orderMasterDao.updateStatus(orderMaster);
        if(i==0){
            log.error("[完结订单  更新失败]，orderMaster={} " ,orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[订单支付完成] 订单状态不正确 , orderId= {},orderStatus ={}",orderDTO.getOrderId(),orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("[订单支付完成] 订单支付状态不正确 , orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        int i = orderMasterDao.updateStatus(orderMaster);
//        System.out.println("------------- &&&$$$$$$$$$*****  "+ i  +"$$$$$$$$$");
        if(i==0){
            log.error("[订单支付完成] 更新失败 ， orderMaster = {} " ,orderMaster);
            throw  new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    //卖家查询订单
    @Override
    public PageInfo<OrderDTO> findList(int page,int pageSize) {
        //设置分页
        PageHelper.startPage(page,pageSize);
        List<OrderMaster> orderMasterList = orderMasterDao.findAll();
        List<OrderDTO> orderDTOList = OrderMasterShiftOrderDTO.convert(orderMasterList);
        return new PageInfo<>(orderDTOList);
    }
}
