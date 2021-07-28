package com.mengzhiayuan.naruto.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * 商品/包含类目
 * @date ：2021/7/20 13:25
 */

@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = -1367282470627225265L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer catagoryType;

    /*json的嵌套对象*/
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
