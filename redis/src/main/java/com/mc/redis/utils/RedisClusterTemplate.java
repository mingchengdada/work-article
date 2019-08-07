package com.mc.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.nio.charset.Charset;
import java.util.*;

/**
 * 项目名称:  pinkstone
 * 包:      com.migu.pinkstone.common.util
 * 类名称:   userinfo
 * 类描述:   类功能详细描述
 * 创建人:    liyin
 * 创建时间:  2017/12/5 15:28
 */
@Component
public class RedisClusterTemplate implements RedisTemplate {

    //默认字符集为UTF8
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF8");

    public static final Map<String, Long>  UNIQUE_MAP_USER = new HashMap<>();
    public static final Map<String, Long>  UNIQUE_MAP_BLACK_RULE = new HashMap<>();
    public static final Map<String, Long>  UNIQUE_MAP_BLACK = new HashMap<>();
    public static final Map<String, Long>  UNIQUE_MAP_MQ = new HashMap<>();
    public static final Map<String, Long>  UNIQUE_MAP_BALANCE = new HashMap<>();
    public static final Map<String, Long>  UNIQUE_MAP_PRAISE = new HashMap<>();
    public static final Map<String, Long>  UNIQUE_MAP_COMMENT = new HashMap<>();
    public static final Map<String, Long>  UNIQUE_MAP_COLLECTION = new HashMap<>();
    public static final Map<String, Long>  UNIQUE_MAP_REWARD_FLOW = new HashMap<>();
    public static final Map<String, Long>  UNIQUE_MAP_NOTIFY = new HashMap<>();
    //Redis集群客户端
    @Autowired
    private JedisCluster jedisCluster;

    //Json序列化器
    @Autowired
    private GenericJackson2JsonRedisSerializer serializer;

    /**
     * Set the value with key
     *
     * @param key   待设置的key
     * @param value 待设置的值
     * @return 状态
     */
    @Override
    public String set(String key, Object value) {
        return set(key, value, DEFAULT_CHARSET);
    }

    /**
     * Set the value with key
     *
     * @param key     待设置的key
     * @param value   待设置的值
     * @param charset 字符集
     * @return 状态码 string
     */
    public String set(String key, Object value, Charset charset) {
        return jedisCluster.set(key.getBytes(charset), serializer.serialize(value));
    }

    /**
     * Set the value with key if key is not available
     *
     * @param key   待设置的key
     * @param value 待设置的值
     * @return 状态
     */
    @Override
    public Long setNx(String key, Object value) {
        return setNx(key, value, DEFAULT_CHARSET);
    }

    /**
     * Set the value with key
     *
     * @param key     待设置的key
     * @param value   待设置的值
     * @param charset 字符集
     * @return 状态码 nx
     */
    public Long setNx(String key, Object value, Charset charset) {
        return jedisCluster.setnx(key.getBytes(charset), serializer.serialize(value));
    }

    /**
     * 设置超时时间
     *
     * @param key     待设置超时时间的key
     * @param seconds 超时时间
     * @return 状态码 1表示设置成功 0表示此key已经设置了过期时间
     */
    @Override
    public Long expire(String key, int seconds) {
        return expire(key, seconds, DEFAULT_CHARSET);
    }

    /**
     * 设置超时时间
     *
     * @param key     待设置超时时间的key
     * @param seconds 超时时间
     * @param charset 字符集
     * @return 状态码 1表示设置成功 0表示此key已经设置了过期时间
     */
    public Long expire(String key, int seconds, Charset charset) {
        return jedisCluster.expire(key.getBytes(charset), seconds);
    }

    /**
     * 设置超时时间
     *
     * @param key          待设置超时时间的key
     * @param milliseconds 超时时间,毫秒
     * @return 状态码 1表示设置成功 0表示此key已经设置了过期时间
     */
    @Override
    public Long pexpire(String key, long milliseconds) {
        return pexpire(key, milliseconds, DEFAULT_CHARSET);
    }

    /**
     * 设置超时时间
     *
     * @param key          待设置超时时间的key
     * @param milliseconds 超时时间,毫秒
     * @param charset      字符集
     * @return 状态码 1表示设置成功 0表示此key已经设置了过期时间
     */
    public Long pexpire(String key, long milliseconds, Charset charset) {
        return jedisCluster.pexpire(key.getBytes(charset), milliseconds);
    }

    /**
     * Set the string value with key
     *
     * @param key   待设置的key
     * @param value 待设置的value
     * @return 状态码
     */
    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    /**
     * Set the object value with key if not key is not available
     *
     * @param key   待设置的key
     * @param value 待设置的value
     * @return 状态码
     */
    public Long setNx(String key, String value) {
        return jedisCluster.setnx(key, value);
    }

    /***
     *
     * 查看key对应的value剩余时间
     * @param key
     * @return 剩余时间，单位秒
     */
    @Override
    public Long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    /**
     * 查看key对应的value剩余时间
     *
     * @param key key
     * @return 剩余时间，单位秒
     */
    @Override
    public Long ttl(byte[] key) {
        return jedisCluster.ttl(key);
    }

    /***
     *
     * 查看key对应的value剩余时间
     * @param key key
     * @return 剩余时间单位毫秒
     */
    @Override
    public Long pttl(String key) {
        return jedisCluster.pttl(key);
    }

    /**
     * 获取key对应的String 类型value
     *
     * @param key key
     * @return value
     */
    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    /**
     * 获取key对应的value并将其转换为对应的实例
     *
     * @param key   key
     * @param clazz 待转换实例的Class
     * @return 对应的实例
     */
    @Override
    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, DEFAULT_CHARSET);
    }

    /**
     * 获取key对应的value并将其转换为对应的实例
     *
     * @param <T>     t
     * @param key     key
     * @param clazz   待转换实例的Class
     * @param charset 字符集
     * @return 对应的实例 t
     */
    public <T> T get(String key, Class<T> clazz, Charset charset) {
        byte[] value = jedisCluster.get(key.getBytes(charset));
        return serializer.deserialize(value, clazz);
    }

    /**
     * 删除key对应的value
     *
     * @param key key
     * @return 状态码
     */
    @Override
    public Long del(String key) {
        return del(key, DEFAULT_CHARSET);
    }

    /**
     * 删除key对应的value
     *
     * @param key     key
     * @param charset 字符集
     * @return 状态码 long
     */
    public Long del(String key, Charset charset) {
        return jedisCluster.del(key.getBytes(charset));
    }

    /**
     * 删除一组key对应的value
     *
     * @param keys key
     * @return 状态码
     */
    @Override
    public Long del(String... keys) {
        return jedisCluster.del(keys);
    }

    /**
     * hset
     *
     * @param key   对应的key
     * @param field 对应的field
     * @param value 对应的value
     * @return 状态码
     */
    @Override
    public Long hset(String key, String field, Object value) {
        return hset(key, field, value, DEFAULT_CHARSET);
    }

    /**
     * hset
     *
     * @param key     对应的key
     * @param field   对应的field
     * @param value   对应的value
     * @param charset 字符集
     * @return 状态码 long
     */
    public Long hset(String key, String field, Object value, Charset charset) {
        return jedisCluster.hset(key.getBytes(charset), field.getBytes(charset), serializer.serialize(value));
    }


    /**
     * hsetnx
     *
     * @param key   对应的key
     * @param field 对应的field
     * @param value 对应的value
     * @return 状态码
     */
    public Long hsetNx(String key, String field, Object value) {
        return hsetNx(key, field, value, DEFAULT_CHARSET);
    }

    /**
     * hset
     *
     * @param key     对应的key
     * @param field   对应的field
     * @param value   对应的value
     * @param charset 字符集
     * @return 状态码 long
     */
    public Long hsetNx(String key, String field, Object value, Charset charset) {
        return jedisCluster.hsetnx(key.getBytes(charset), field.getBytes(charset), serializer.serialize(value));
    }

    /**
     * hget
     *
     * @param key   对应的key
     * @param field 对应的value
     * @param clazz 对应实例的Class
     * @return 对应的实例
     */
    @Override
    public <T> T hget(String key, String field, Class<T> clazz) {
        return hget(key, field, clazz, DEFAULT_CHARSET);
    }

    /**
     * hget
     *
     * @param <T>     t
     * @param key     对应的key
     * @param field   对应的value
     * @param clazz   对应实例的Class
     * @param charset 字符集
     * @return 对应的实例 t
     */
    public <T> T hget(String key, String field, Class<T> clazz, Charset charset) {
        byte[] value = jedisCluster.hget(key.getBytes(charset), field.getBytes(charset));
        return serializer.deserialize(value, clazz);
    }

    /**
     * hget
     *
     * @param key   对应的key
     * @param field 对应的value
     * @return 对应的实例
     */
    @Override
    public String hget(String key, String field) {

        return jedisCluster.hget(key, field);
    }

    /**
     * hdel
     *
     * @param key   对应的key
     * @param field 对应的field
     * @return 状态码
     */
    @Override
    public Long hdel(String key, String field) {
        return hdel(key, field, DEFAULT_CHARSET);
    }

    /**
     * hdel
     *
     * @param key     对应的key
     * @param field   对应的field
     * @param charset 字符集
     * @return 状态码 long
     */
    public Long hdel(String key, String field, Charset charset) {

        return jedisCluster.hdel(key.getBytes(charset), field.getBytes(charset));
    }

    /**
     * hdel
     *
     * @param key    对应的key
     * @param fields 对应的field数组
     * @return 状态码
     */
    @Override
    public Long hdel(String key, String... fields) {
        return jedisCluster.hdel(key, fields);
    }

    /**
     * hkeys
     *
     * @param key 对应的key
     * @return key集合
     */
    @Override
    public Set<String> hkeys(String key) {
        return jedisCluster.hkeys(key);
    }

    /**
     * hkeys
     *
     * @param key 对应的key
     * @return key集合
     */
    @Override
    public Set<byte[]> hkeysWithBytes(String key) {
        return hkeysWithBytes(key, DEFAULT_CHARSET);
    }

    /**
     * hkeys
     *
     * @param key     对应的key
     * @param charset 字符集
     * @return key集合 set
     */
    public Set<byte[]> hkeysWithBytes(String key, Charset charset) {
        return jedisCluster.hkeys(key.getBytes(charset));
    }

    /**
     * hmset
     *
     * @param key   对应的key
     * @param value 对应的value
     * @return fields
     */
    @Override
    public <T> String hmset(String key, Map<String, T> value) {
        return hmset(key, value, DEFAULT_CHARSET);
    }

    /**
     * hmset
     *
     * @param <T>     t
     * @param key     对应的key
     * @param value   对应的value
     * @param charset 字符集
     * @return fields string
     */
    public <T> String hmset(String key, Map<String, T> value, Charset charset) {
        Map<byte[], byte[]> hash = new HashMap<>();
        for (Map.Entry<String, T> entry : value.entrySet()) {
            hash.put(entry.getKey().getBytes(charset), serializer.serialize(entry.getValue()));
        }
        return jedisCluster.hmset(key.getBytes(charset), hash);
    }

    /**
     * hmget
     *
     * @param key    对应的key
     * @param fields 对应的fields列表
     * @return
     */
    @Override
    public Map<String, byte[]> hmget(String key, List<String> fields) {

        return hmget(key, fields, DEFAULT_CHARSET);
    }

    /**
     * hmget
     *
     * @param key     对应的key
     * @param fields  对应的fields列表
     * @param charset 字符集
     * @return map map
     */
    public Map<String, byte[]> hmget(String key, List<String> fields, Charset charset) {
        Map<String, byte[]> resultMap = new HashMap<>();
        List<byte[]> byteFields = new ArrayList<>(fields.size());
        for (String field : fields) {
            byte[] byteField = field.getBytes(charset);
            byteFields.add(byteField);
        }
        byte[][] bytes = byteFields.toArray(new byte[fields.size()][]);
        List<byte[]> result = jedisCluster.hmget(key.getBytes(charset), bytes);
        for (int i = 0; i < fields.size(); i++) {
            resultMap.put(fields.get(i), result.get(i));
        }
        return resultMap;
    }

    /***
     *
     * 判断key是否存在
     * @param key
     * @return true存在，false不存在
     */
    @Override
    public boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    /**
     * 判断key下的field是否存在
     *
     * @param key   key
     * @param field field
     * @return true存在，false不存在
     */
    @Override
    public boolean hexists(String key, String field) {
        return jedisCluster.hexists(key.getBytes(DEFAULT_CHARSET), field.getBytes(DEFAULT_CHARSET));
    }



    /**
     * 随机数生成.
     * 生成9位随机数
     * @return String
     */
    public String getRandom() {
        StringBuilder str = new StringBuilder(); // 定义变长字符串
        Random random = new Random();
        // 随机生成数字，并添加到字符串
        for (int i = 0; i < 9; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

}
