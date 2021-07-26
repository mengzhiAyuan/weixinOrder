package com.mengzhiayuan.naruto.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengzhiayuan.naruto.dao.ProductDao;
import com.mengzhiayuan.naruto.dto.CartDTO;
import com.mengzhiayuan.naruto.entity.ProductInfo;
import com.mengzhiayuan.naruto.enums.ProductStatusEnum;
import com.mengzhiayuan.naruto.enums.ResultEnum;
import com.mengzhiayuan.naruto.exception.SellException;
import com.mengzhiayuan.naruto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/19 18:04
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
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
}
