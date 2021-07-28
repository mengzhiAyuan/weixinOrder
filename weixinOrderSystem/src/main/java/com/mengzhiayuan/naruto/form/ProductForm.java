package com.mengzhiayuan.naruto.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/26 21:21
 */

@Data
public class ProductForm {

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

}
