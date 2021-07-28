package com.mengzhiayuan.naruto.service.Impl;


import com.mengzhiayuan.naruto.dao.SellerInfoDao;
import com.mengzhiayuan.naruto.entity.SellerInfo;
import com.mengzhiayuan.naruto.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/27 11:34
 */

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoDao.findByOpenid(openid);
    }
}
