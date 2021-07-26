package com.mengzhiayuan.naruto.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mengzhiayuan.naruto.entity.OrderDetail;
import com.mengzhiayuan.naruto.enums.OrderStatusEnum;
import com.mengzhiayuan.naruto.enums.PayStatusEnum;
import com.mengzhiayuan.naruto.utils.DateShiftLong;
import com.mengzhiayuan.naruto.utils.EnumUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ：mengzhiayuan
 * 在一个Web服务的实现中，我们常常需要访问数据库，并将从数据库中所取得的数据显示在用户页面中。
 * 这样做的一个问题是：用于在用户页面上展示的数据和从数据库中取得的数据常常具有较大区别。
 * 在这种情况下，我们常常需要向服务端发送多个请求才能将用于在页面中展示的数据凑齐。
 *
 * 一个解决该问题的方法就是根据不同需求使用不同的数据表现形式。
 * 在一个服务实现中较为常见的数据表现形式有MO（Model Object，
 * 在有些上下文中也被称为VO，Value Object）和DTO（Data Transfer Object）。
 * MO用来表示从数据库中读取的数据，而DTO则用来表示在网络上所传输的数据
 *
 * @date ：2021/7/20 17:37
 */

//数据传输对象   买家端订单列表展示

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    /** 订单id. */
    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus;

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus;

    /** 创建时间. */
    @JsonSerialize(using= DateShiftLong.class)
    private Date createTime;

    /** 更新时间. */
    @JsonSerialize(using= DateShiftLong.class)
    private Date updateTime;

    //考虑到一个订单可能有多单，master有多个详情页
    List<OrderDetail> orderDetailList;

    //通过code来获取枚举
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }


}
