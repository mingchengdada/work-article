/**
 * All rights Reserved, Designed By MiGu
 * Copyright:    Copyright(C) 2016-2020
 * Company       MiGu  Co., Ltd.
 */

package com.mc.redis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 一句话功能描述
 * 项目名称:  pinkstone-common
 * 包:      com.migu.pinkstone.common.redis
 * 类名称:   RedisClusterProperties
 * 类描述:   类功能详细描述
 * 创建人:   frank
 * 创建时间:  2017 下午2:55:23
 */
@Component
@Data
@ConfigurationProperties(prefix = "redis.cluster")
public class RedisClusterProperties {

    //链接超时时间
    private String timeout;

    //集群节点信息:形如 xxx.xxx.xxx.xxx:port,xxx.xxx.xxx.xxx:port
    private String nodes;

    //重连次数
    private String maxAttempts;
}
