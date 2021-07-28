package com.mengzhiayuan.naruto.entity;

import lombok.Data;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/27 11:07
 */

@Data
public class SellerInfo {

    private String sellerId;

    private String username;

    private String password;

    //openid是微信用户在公众号appid下的唯一用户标识（appid不同，则获取到的openid就不同），
    // 可用于永久标记一个用户，同时也是微信JSAPI支付的必传参数。
    private String openid;
}
