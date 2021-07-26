package com.mengzhiayuan.naruto.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum{

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    ;

    private Integer code;
    private String message;

    //构造函数必须私有化。事实上，private是多余的，你完全没有必要写，
    // 因为它默认并强制是private，如果你要写，也只能写private，写public是不能通过编译的
    PayStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
