package com.mengzhiayuan.naruto.form;

import lombok.Data;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/26 21:53
 */

@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
