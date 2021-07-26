package com.mengzhiayuan.naruto.utils;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/25 15:02
 */
public class MathUtil {
    private static final Double MONEY_RANGE=0.01;
    /**
     * 比较两个金额是否相等
    **/

    public static Boolean equals(Double d1,Double d2){
        Double result=Math.abs(d1-d2);
        if(result<MONEY_RANGE){
            return true;
        }else{
            return false;
        }
    }
}
