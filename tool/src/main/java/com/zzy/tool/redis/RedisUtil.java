package com.zzy.tool.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public final class RedisUtil {
    
    //Redis服务器IP
    private static String ADDR = "192.168.0.100";
    
    //Redis的端口号
    private static int PORT = 6379;
    
    //访问密码
    private static String AUTH = "admin";
     
    
    private static int TIMEOUT = 10000;
    
    
    private static JedisPool jedisPool = null;
    
    /**
     * 初始化Redis连接池
     */
    static {
        try {
        	JedisPoolConfig config = new JedisPoolConfig();
			// 最大连接数, 默认10个
			config.setMaxTotal(RedisConfig.maxTotal);
			// 最大空闲连接数, 默认10个
			config.setMaxIdle(RedisConfig.maxIdle);
			// 最小连接数, 默认10个
			config.setMinIdle(RedisConfig.minIdle);
			//最大等待时间
			config.setMaxWaitMillis(RedisConfig.maxWait);
			// 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
			config.setMaxWaitMillis(RedisConfig.maxWait);
			// 在获取连接的时候检查connection有效性, 默认false
			config.setTestOnBorrow(RedisConfig.testOnBorrow);
			//在进行returnObject对返回的connection进行validateObject校验
			config.setTestOnReturn(RedisConfig.testOnBorrow);
			// 在空闲时检查连接池有效性, 默认false
			config.setTestWhileIdle(RedisConfig.testWhileIdle);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取Jedis实例
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
}