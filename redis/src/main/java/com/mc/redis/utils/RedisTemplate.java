package com.mc.redis.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 项目名称:  pinkstone
 * 包:      com.migu.pinkstone.common.util
 * 类名称:   userinfo
 * 类描述:   类功能详细描述
 * 创建人:    liyin
 * 创建时间:  2017/12/5 15:26
 */
public interface RedisTemplate {
    /**
     * Set the object value with key
     *
     * @param key   待设置的key
     * @param value 待设置的value
     * @return 状态码 string
     */
    String set(String key, Object value);

    /**
     * Set the string value with key
     *
     * @param key   待设置的key
     * @param value 待设置的value
     * @return 状态码 string
     */
    String set(String key, String value);

    /**
     * Set the object value with key if not key is not available
     *
     * @param key   待设置的key
     * @param value 待设置的value
     * @return 状态码 nx
     */
    Long setNx(String key, Object value);

    /**
     * Set the object value with key if not key is not available
     *
     * @param key   待设置的key
     * @param value 待设置的value
     * @return 状态码 nx
     */
    Long setNx(String key, String value);

    /**
     * 设置超时时间
     *
     * @param key     待设置超时时间的key
     * @param seconds 超时时间
     * @return 状态码 1表示设置成功 0表示此key已经设置了过期时间
     */
    Long expire(String key, int seconds);

    /**
     * 设置超时时间
     *
     * @param key          待设置超时时间的key
     * @param milliseconds 超时时间,毫秒
     * @return 状态码 1表示设置成功 0表示此key已经设置了过期时间
     */
    Long pexpire(String key, long milliseconds);

    /***
     *
     * 查看key对应的value剩余时间
     * @param key key
     * @return 剩余时间单位为秒 long
     */
    Long ttl(String key);

    /***
     *
     * 查看key对应的value剩余时间
     * @param key key
     * @return 剩余时间单位为秒 long
     */
    Long ttl(byte[] key);

    /***
     *
     * 查看key对应的value剩余时间
     * @param key key
     * @return 剩余时间单位毫秒 long
     */
    Long pttl(String key);

    /**
     * 获取key对应的String 类型value
     *
     * @param key key
     * @return value string
     */
    String get(String key);

    /**
     * 获取key对应的value并将其转换为对应的实例
     *
     * @param <T>   t
     * @param key   key
     * @param clazz 待转换实例的Class
     * @return 对应的实例 t
     */
    <T> T get(String key, Class<T> clazz);

    /**
     * 删除key对应的value
     *
     * @param key key
     * @return 状态码 long
     */
    Long del(String key);

    /**
     * 删除一组key对应的value
     *
     * @param keys key
     * @return 状态码 long
     */
    Long del(String... keys);

    /**
     * hset
     *
     * @param key   对应的key
     * @param field 对应的field
     * @param value 对应的value
     * @return 状态码 long
     */
    Long hset(String key, String field, Object value);

    /**
     * hsetnx
     *
     * @param key   对应的key
     * @param field 对应的field
     * @param value 对应的value
     * @return 状态码 long
     */
    Long hsetNx(String key, String field, Object value);


    /**
     * hget
     *
     * @param <T>   t
     * @param key   对应的key
     * @param field 对应的value
     * @param clazz 实例对应的Class
     * @return 对应的实例 t
     */
    <T> T hget(String key, String field, Class<T> clazz);

    /**
     * hget
     *
     * @param key   对应的key
     * @param field 对应的value
     * @return 对应的实例 string
     */
    String hget(String key, String field);

    /**
     * hdel
     *
     * @param key   对应的key
     * @param field 对应的field
     * @return 状态码 long
     */
    Long hdel(String key, String field);

    /**
     * hdel
     *
     * @param key    对应的key
     * @param fields 对应的field数组
     * @return 状态码 long
     */
    Long hdel(String key, String... fields);

    /**
     * hkeys
     *
     * @param key 对应的key
     * @return key集合 set
     */
    Set<String> hkeys(String key);

    /**
     * hkeys
     *
     * @param key 对应的key
     * @return key集合 set
     */
    Set<byte[]> hkeysWithBytes(String key);

    /**
     * hmset
     *
     * @param <T>   t
     * @param key   对应的key
     * @param value 对应的value
     * @return fields string
     */
    <T> String hmset(String key, Map<String, T> value);

    /**
     * hmget
     *
     * @param key    对应的key
     * @param fields 对应的fields列表
     * @return map map
     */
    Map<String, byte[]> hmget(String key, List<String> fields);

    /***
     *
     * 判断key是否存在
     * @param key the key
     * @return true存在 ，false不存在
     */
    boolean exists(String key);

    /**
     * 判断key下的field是否存在
     *
     * @param key   key
     * @param field field
     * @return true存在 ，false不存在
     */
    boolean hexists(String key, String field);
}
