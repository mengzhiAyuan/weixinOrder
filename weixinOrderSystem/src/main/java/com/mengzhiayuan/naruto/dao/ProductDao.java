package com.mengzhiayuan.naruto.dao;



import com.mengzhiayuan.naruto.entity.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/* 买家商品 */
@Repository
@Mapper
public interface ProductDao {
    //通过商品id找商品
    ProductInfo findOne(String productId);

    //通过状态码来找商品
    List<ProductInfo> findByProductStatus(Integer code);

    //找到所有的商品----》》》分页在service层执行
    List<ProductInfo> findAll();

    //存一个商品
    void save(ProductInfo productInfo);

    //修改库存
    void update(ProductInfo productInfo);
}
