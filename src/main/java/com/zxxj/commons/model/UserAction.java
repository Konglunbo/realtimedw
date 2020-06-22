package com.zxxj.commons.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 用户访问动作表
 */
@Builder(toBuilder = true)
@Data
@ToString
public class UserAction {
    /**
     * 用户点击行为的日期
     */
    private String date;
    /**
     * 用户的ID
     */
    private Long user_id;
    /**
     * Session的ID
     */
    private String session_id;
    /**
     * 页面ID
     */
    private Long page_id;
    /**
     * 点击行为时间点
     */
    private String action_time;
    /**
     * 用户搜索的关键词
     */
    private String search_keyword;
    /**
     * 商品品类的ID
     */
    private Long click_category_id;
    /**
     * 商品的ID
     */
    private Long click_product_id;
    /**
     *一次订单中所有品类的ID集合
     */
    private String order_category_ids;
    /**
     * 一次订单中所有商品的ID集合
     */
    private String order_product_ids;
    /**
     * 一次支付中所有品类的ID集合
     */
    private String pay_category_ids;
    /**
     * 一次支付中所有商品的ID集合
     */
    private String pay_product_ids;
    /**
     * 城市ID
     */
    private Long city_id;
}