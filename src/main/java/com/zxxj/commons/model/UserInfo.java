package com.zxxj.commons.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * 用户信息表
 */
@Builder(toBuilder = true)
@Data
@ToString
public class UserInfo {
    /**
     * 用户ID
     */
    private Long userid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 职业
     */
    private String professional;
    /**
     * 所在城市
     */
    private String city;
    /**
     * 性别
     */
    private String sex;

}
