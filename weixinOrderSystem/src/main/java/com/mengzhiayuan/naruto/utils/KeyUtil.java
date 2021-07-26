package com.mengzhiayuan.naruto.utils;

import java.util.Random;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * 生成唯一的主键
 * 格式： 时间+随机数
 * @date ：2021/7/20 18:43
 */
public class KeyUtil {

    public static String getUniqueKey(){
        Random random =new Random();
        Integer number =random.nextInt(9000000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
