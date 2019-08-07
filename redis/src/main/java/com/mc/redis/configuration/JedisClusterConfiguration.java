/**
 * All rights Reserved, Designed By MiGu
 * Copyright:    Copyright(C) 2016-2020
 * Company       MiGu  Co., Ltd.
 */

package com.mc.redis.configuration;

import com.mc.redis.properties.RedisClusterProperties;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * 一句话功能描述
 * 项目名称:  pinkstone-api
 * 包:      com.migu.pinkstone.config
 * 类名称:   JedisClusterConfig
 * 类描述:   类功能详细描述
 * 创建人:   frank
 * 创建时间:  2017 下午3:45:33
 */
@Configuration
public class JedisClusterConfiguration {

    private static final int IP = 0;

    private static final int PORT = 1;

    @Autowired
    private RedisClusterProperties redisClusterProperties;

    public @Bean
    RedisConnectionFactory connectionFactory() {
        String[] servers = redisClusterProperties.getNodes().split(",");
        return new JedisConnectionFactory(
                new RedisClusterConfiguration(Arrays.asList(servers)));
    }

    /**
     * Gets jedis cluster.
     *
     * @return JedisCluster jedis cluster
     */
    @Bean
    public JedisCluster getJedisCluster() {
        String[] servers = redisClusterProperties.getNodes().split(",");
        Set<HostAndPort> nodes = new HashSet<>();
        for (String server : servers) {
            String[] ipAndPort = server.split(":");
            nodes.add(new HostAndPort(ipAndPort[IP], Integer.parseInt(ipAndPort[PORT])));
        }

        return new JedisCluster(nodes, Integer.parseInt(redisClusterProperties.getTimeout()),
                Integer.parseInt(redisClusterProperties.getMaxAttempts()), getPoolConfig());
    }

    /**
     * Gets pool config.
     *
     * @return the pool config
     */
    @ConfigurationProperties(prefix = "redis.cluster")
    @Bean
    public GenericObjectPoolConfig getPoolConfig() {
        return new GenericObjectPoolConfig();
    }

    /**
     * Gets redis serializer.
     *
     * @return the redis serializer
     */
    @Bean
    public GenericJackson2JsonRedisSerializer getRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
}
