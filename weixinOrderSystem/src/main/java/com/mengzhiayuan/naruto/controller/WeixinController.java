package com.mengzhiayuan.naruto.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：mengzhiayuan
 * @description
 * @date ：2021/7/23 18:01
 */
//如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE。

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法");
        log.info("code={}",code);
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx0e52271d332e0def&secret=79637dc1906d72fac1933051847f06b6&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        //getForObject函数实际上是对getForEntity函数的进一步封装，只关注返回的消息体的内容，对其他信息都不关注
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}",response);
    }

}
