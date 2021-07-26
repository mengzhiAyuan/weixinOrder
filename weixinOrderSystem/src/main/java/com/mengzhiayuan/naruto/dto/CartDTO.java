package com.mengzhiayuan.naruto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/20 20:36
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    /** 商品id. */
    private String productId;

    /** 商品数量. */
    private Integer productQuantity;
}
