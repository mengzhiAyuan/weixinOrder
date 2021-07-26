package com.mengzhiayuan.naruto.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * --------------http请求返回的最外层对象----------------
 * @date ：2021/7/20 12:23
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    /*错误码*/
    private Integer code;

    /*提示信息*/
    private String msg;

    /*具体内容*/
    private T data;
}
