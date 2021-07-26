package com.mengzhiayuan.naruto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * ProductCategory-----类目表
 * @date ：2021/7/17 20:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {

    /* 类目 */
    private Integer categoryId;
    /*类目名字*/
    private String categoryName;
    /*类目编号*/
    private Integer categoryType;

    private Date createTime;
    private Date updateTime;
}
