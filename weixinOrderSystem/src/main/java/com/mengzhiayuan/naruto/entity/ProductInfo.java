package com.mengzhiayuan.naruto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * 商品表 ------------》》》买家端商品
 * @date ：2021/7/19 15:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {

    private String productId;
    /*名字*/
    private String productName;
    /*单价*/
    private BigDecimal productPrice;
    /*库存*/
    private Integer productStock;
    /*描述*/
    private String productDescription;
    /*小图*/
    private String productIcon;
    /*状态  0 正常 , 1 下架*/
    private Integer productStatus;
    /*类目编号*/
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;
}
