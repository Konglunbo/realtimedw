package com.zxxj.commons.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 产品表
 */
@Builder(toBuilder = true)
@Getter
@ToString
public class ProductInfo {
    /**
     * 商品id
     */
    private Long product_id;
    /**
     * 商品名称
     */
    private String product_name;
    /**
     * 商品扩展信息
     */
    private String extend_info;
}
