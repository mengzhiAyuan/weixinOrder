package com.mengzhiayuan.naruto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/20 16:58
 */

//商品详情表

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    private String detailId;

    /** 订单id. */
    private String orderId;

    /** 商品id. */
    private String productId;

    /** 商品名称. */
    private String productName;

    /** 商品单价. */
    private BigDecimal productPrice;

    /** 商品数量. */
    private Integer productQuantity;

    /** 商品小图. */
    private String productIcon;
}
