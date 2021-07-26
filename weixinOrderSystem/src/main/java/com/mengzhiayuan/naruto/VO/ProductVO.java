package com.mengzhiayuan.naruto.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * 商品/包含类目
 * @date ：2021/7/20 13:25
 */

@Data
public class ProductVO {
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer catagoryType;

    /*json的嵌套对象*/
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
