package com.mengzhiayuan.naruto.form;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/22 15:42
 */

//表单验证，即校验用户提交的数据的合理性
//添加message的值，指示如果不符合的话，会得到什么message

@Data
public class OrderForm {

    //买家姓名
    @NotEmpty(message = "姓名必填")
    private String name;

    //买家手机号
    @NotEmpty(message = "手机号必填")
    private String phone;

    //买家地址
    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "购物车不能为空")
    private String items;

    //买家微信openid
    @NotEmpty(message = "openid必填")
    private String openid;

}
