package com.mengzhiayuan.naruto.controller;


import com.mengzhiayuan.naruto.VO.ProductInfoVO;
import com.mengzhiayuan.naruto.VO.ProductVO;
import com.mengzhiayuan.naruto.VO.ResultVO;
import com.mengzhiayuan.naruto.entity.ProductCategory;
import com.mengzhiayuan.naruto.entity.ProductInfo;
import com.mengzhiayuan.naruto.service.CategoryService;
import com.mengzhiayuan.naruto.service.ProductService;
import com.mengzhiayuan.naruto.utils.ResultVOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * ------------买家商品------------
 * @date ：2021/7/20 12:18
 */

// 买家api

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product",key = "123")
    public ResultVO list(){

        //1.查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        System.out.println(productInfoList);

        //查询不要放到循环里头，影响性能！！！
        //2.查询类目，一次性查询
        List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList ){
            categoryTypeList.add(productInfo.getCategoryType());
        }
        System.out.println(categoryTypeList);
        List<ProductCategory> productCategoryList
                = categoryService.findByCategoryTypeIn(categoryTypeList);

        System.out.println(productCategoryList);


        //3.数据拼装  先是类目，再是类目下的每一个商品
        List<ProductVO> productVOList=new ArrayList<>();
        for (ProductCategory productCategory: productCategoryList){
            ProductVO productVO=new ProductVO();
            System.out.println(productCategory);
            productVO.setCatagoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> productInfoVOList=new ArrayList<>();
            for (ProductInfo productInfo : productInfoList){
                //遍历所有的上架商品，必须是当前的类别下才行
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO=new ProductInfoVO();
                    productInfoVO.setProductId(productInfo.getProductId());
                    productInfoVO.setProductName(productInfo.getProductName());
                    productInfoVO.setProductPrice(productInfo.getProductPrice());
                    productInfoVO.setProductDescription(productInfo.getProductDescription());
                    productInfoVO.setProductIcon(productInfo.getProductIcon());
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtils.success(productVOList);
    }
}
