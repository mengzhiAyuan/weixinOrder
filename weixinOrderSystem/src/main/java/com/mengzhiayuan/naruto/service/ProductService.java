package com.mengzhiayuan.naruto.service;


import com.github.pagehelper.PageInfo;
import com.mengzhiayuan.naruto.dto.CartDTO;
import com.mengzhiayuan.naruto.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
/*
买家商品
**/
public interface ProductService {

    ProductInfo findOne(String productId);

    /*查询所有在架商品列表*/
    List<ProductInfo> findUpAll();

    List<ProductInfo> findAll(int page, int pagesize);

    Page<ProductInfo> findAll(Pageable pageable);

    void save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);

}
