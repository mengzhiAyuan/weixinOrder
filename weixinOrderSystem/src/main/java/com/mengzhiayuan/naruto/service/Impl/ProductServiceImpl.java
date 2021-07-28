package com.mengzhiayuan.naruto.service.Impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengzhiayuan.naruto.dao.ProductDao;
import com.mengzhiayuan.naruto.dto.CartDTO;
import com.mengzhiayuan.naruto.entity.ProductInfo;
import com.mengzhiayuan.naruto.enums.ProductStatusEnum;
import com.mengzhiayuan.naruto.enums.ResultEnum;
import com.mengzhiayuan.naruto.exception.SellException;
import com.mengzhiayuan.naruto.service.ProductService;
import com.mengzhiayuan.naruto.utils.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/19 18:04
 */

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * cacheNames/value ：用来指定缓存组件的名字
     * key ：缓存数据时使用的 key，可以用它来指定。默认是使用方法参数的值。（这个 key 你可以使用 spEL 表达式来编写）
     * */
    @Override
    @Cacheable(cacheNames = "product",key = "123")
    public ProductInfo findOne(String productId) {
        return productDao.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findAll(int page,int pagesize){
        PageHelper.startPage(page,pagesize);
        return productDao.findAll();
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize());
        List<ProductInfo> productInfoList=productDao.findAll();
        return new PageImpl<>(productInfoList);
    }

    @Override
    @CachePut(cacheNames = "product",key = "123")
    public void save(ProductInfo productInfo) {
        productDao.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo = productDao.findOne(cartDTO.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer result = productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productDao.update(productInfo);
        }
    }


    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){   //每一个产品减库存
            ProductInfo productInfo = productDao.findOne(cartDTO.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer result = productInfo.getProductStock()- cartDTO.getProductQuantity();
            if (result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productDao.update(productInfo);
        }
    }

    //上架
    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo=productDao.findOne(productId);
        if(productInfo==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
        }
        if(productInfo.getProductStatusEnum()==ProductStatusEnum.UP){
            log.error("[产品状态不正确，已经上架了不能够再次上架],产品={}",productId);
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productDao.update(productInfo);
        return productInfo;
    }

    //下架
    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo=productDao.findOne(productId);
        if(productInfo==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
        }
        if(productInfo.getProductStatusEnum()==ProductStatusEnum.DOWN){
            log.error("[产品状态不正确，已经上架了不能够再次上架],产品={}",productId);
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productDao.update(productInfo);
        return productInfo;
    }
}
