package com.mengzhiayuan.naruto.enums;

import lombok.Getter;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/20 16:45
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{

    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消"),
    ;
    private Integer code;
    private String message;

    //构造函数必须私有化。事实上，private是多余的，你完全没有必要写，
    // 因为它默认并强制是private，如果你要写，也只能写private，写public是不能通过编译的
    OrderStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
