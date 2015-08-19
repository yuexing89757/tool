package com.zzy.tool.redis;

import com.zzy.tool.util.ConfigManager;


public class RedisConfig {
	public static String ip = (String) ConfigManager.getInstance().getConfigItem("redis.ip"); // ip地址
	public static int port = Integer.parseInt((String) ConfigManager.getInstance().getConfigItem("redis.port")); // 端口
	public static int maxTotal = Integer.parseInt((String) ConfigManager.getInstance().getConfigItem("redis.maxTotal")); // 最大连接数
	public static int maxIdle = Integer.parseInt((String) ConfigManager.getInstance().getConfigItem("redis.maxIdle")); // 最大空闲连接数
	public static int minIdle = Integer.parseInt((String) ConfigManager.getInstance().getConfigItem("redis.minIdle")); // 初始化连接数
	public static Long maxWait = Long.parseLong((String) ConfigManager.getInstance().getConfigItem("redis.maxWait")); // 最大等待时间
	public static Boolean testOnBorrow = Boolean.parseBoolean((String) ConfigManager.getInstance().getConfigItem("redis.testOnBorrow")); // 对拿到的connection进行validateObject校验
	public static Boolean testOnReturn = Boolean.parseBoolean((String) ConfigManager.getInstance().getConfigItem("redis.testOnReturn")); // 在进行returnObject对返回的connection进行validateObject校验
	public static Boolean testWhileIdle = Boolean.parseBoolean((String) ConfigManager.getInstance().getConfigItem("redis.testWhileIdle")); // 定时对线程池中空闲的链接进行validateObject校验
	public static int timeout=Integer.parseInt((String) ConfigManager.getInstance().getConfigItem("redis.timeout")); // 读取获取连接池超时时间
	public static int retryNum = Integer.parseInt((String) ConfigManager.getInstance().getConfigItem("redis.retryNum")); // 获取实例 重试次数

}
