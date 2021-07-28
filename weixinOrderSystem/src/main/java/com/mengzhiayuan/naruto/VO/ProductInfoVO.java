package com.mengzhiayuan.naruto.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ：mengzhiayuan
 * @description：
 * 商品详情：注意这里不用ProductInfo,
 * 是因为不需要返回所有数据给前端
 * 为了安全起见
    @JsonProperty 此注解用于属性上，作用是把该属性的名称序列化为另外一个名称
    这里是返回前端json数据
  * @date ：2021/7/20 13:30
 */

@Data
public class ProductInfoVO implements Serializable {

    private static final long serialVersionUID = 8440560504936427504L;

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
