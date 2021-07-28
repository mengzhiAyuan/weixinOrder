package com.mengzhiayuan.naruto.exception;

import com.mengzhiayuan.naruto.enums.ResultEnum;
import lombok.Getter;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/20 18:17
 */
@Getter
public class SellException extends RuntimeException{

    private Integer code;
    private String message;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public SellException(Integer code,String message){
        super(message);
        this.code=code;
    }

}
