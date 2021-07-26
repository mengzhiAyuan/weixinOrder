package com.mengzhiayuan.naruto.utils;

import com.mengzhiayuan.naruto.VO.ResultVO;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/20 16:20
 */
public class ResultVOUtils {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setMsg("success");
        resultVO.setCode(0);
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
