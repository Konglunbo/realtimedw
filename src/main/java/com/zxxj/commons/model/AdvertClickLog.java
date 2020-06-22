package com.zxxj.commons.model;

/**
 * 广告点击日志
 */

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 模拟的数据
 * 时间点: 当前时间毫秒
 * userId: 0 - 99
 * 省份、城市 ID相同 ： 0 - 9
 * adid: 0 - 19
 * ((0L,"北京","北京"),(1L,"上海","上海"),(2L,"南京","江苏省"),(3L,"广州","广东省"),(4L,"三亚","海南省"),(5L,"武汉","湖北省"),(6L,"长沙","湖南省"),(7L,"西安","陕西省"),(8L,"成都","四川省"),(9L,"哈尔滨","东北省"))
 * 格式 ：timestamp province city userid adid
 * 某个时间点 某个省份 某个城市 某个用户 某个广告
 */
@Builder(toBuilder = true)
@Data
@ToString
public class AdvertClickLog {
    /**
     * 某个时间点
     */
    private Long timestamp;
    /**
     * 某个省份
     */
    private Integer province;
    /**
     * 某个城市
     */
    private Integer city;
    /**
     * 某个广告
     */
    private Integer adid;
    /**
     * 某个用户
     */
    private Integer userid;
}
