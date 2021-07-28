package com.mengzhiayuan.naruto.handler;

//：@ControllerAdvice：全局捕获异常，异常集中处理，更好的使业务逻辑与异常处理剥离开 把@ControllerAdvice定义在一个类上，
// 该类则负责捕获所有@RequestMapping上发生的异常（包括controller调用的service） 2：@ExceptionHandler(value = Exception.class)：统一处理某一类异常

import com.mengzhiayuan.naruto.VO.ResultVO;
import com.mengzhiayuan.naruto.config.ProjectUrlConfig;
import com.mengzhiayuan.naruto.exception.ResponseBankException;
import com.mengzhiayuan.naruto.exception.SellException;
import com.mengzhiayuan.naruto.exception.SellerAuthorizeException;
import com.mengzhiayuan.naruto.utils.ResultVOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mengzhiayuan
 * 2021-07-29 17:44
 */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    //http://sell.natapp4.cc/sell/wechat/qrAuthorize?returnUrl=http://sell.natapp4.cc/sell/seller/login
    @ExceptionHandler(value = SellerAuthorizeException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e) {
        return ResultVOUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleResponseBankException() {

    }
}
