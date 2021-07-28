package com.mengzhiayuan.naruto.dao;


import com.mengzhiayuan.naruto.entity.SellerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SellerInfoDao {

    SellerInfo findByOpenid(String openid);

}
