package com.mengzhiayuan.naruto.utils;

import com.mengzhiayuan.naruto.enums.CodeEnum;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/25 17:38
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code,Class<T> enumClass){
        for(T each : enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
