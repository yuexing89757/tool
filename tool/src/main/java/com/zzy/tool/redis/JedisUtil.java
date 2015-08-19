package com.zzy.tool.redis;

import java.util.HashMap;
import java.util.Map;

import com.zzy.tool.util.ConfigManager;
import com.zzy.tool.util.Log;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis工具类,用于获取RedisPool. 参考官网说明如下：
 */
public class JedisUtil {
	protected Log log = Log.getLogger(JedisUtil.class);
	static ConfigManager configManager = ConfigManager.getInstance();

	/**
	 * 私有构造器.
	 */
	private JedisUtil() {

	}

	private static Map<String, JedisPool> maps = new HashMap<String, JedisPool>();

	/**
	 * 获取连接池.
	 * 
	 * @return 连接池实例
	 */
	private static JedisPool getPool(String ip, int port) {
		String key = ip + ":" + port;
		JedisPool pool = null;
		if (!maps.containsKey(key)) {
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
			try {
				/**
				 * 如果你遇到 java.net.SocketTimeoutException: Read timed out
				 * exception的异常信息 请尝试在构造JedisPool的时候设置自己的超时值.
				 * JedisPool默认的超时时间是2秒(单位毫秒)
				 */
				pool = new JedisPool(config, ip, port,RedisConfig.timeout);
				maps.put(key, pool);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			pool = maps.get(key);
		}
		return pool;
	}

	/**
	 * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
	 */
	private static class RedisUtilHolder {
		/**
		 * 静态初始化器，由JVM来保证线程安全
		 */
		private static JedisUtil instance = new JedisUtil();
	}

	/**
	 * 当getInstance方法第一次被调用的时候，它第一次读取
	 * RedisUtilHolder.instance，导致RedisUtilHolder类得到初始化；而这个类在装载并被初始化的时候，会初始化它的静
	 * 态域，从而创建RedisUtil的实例，由于是静态的域，因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来保证它的线程安全性。
	 * 这个模式的优势在于，getInstance方法并没有被同步，并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本。
	 */
	public static JedisUtil getInstance() {
		return RedisUtilHolder.instance;
	}

	/**
	 * 获取Redis实例.
	 * 
	 * @return Redis工具类实例
	 */
	public Jedis getJedis(String ip, int port) {
		Jedis jedis = null;
		int count = 0;
		do {
			try {
				jedis = getPool(ip, port).getResource();
			} catch (Exception e) {
				log.error("get redis master1 failed!", e);
				// 销毁对象
				getPool(ip, port).returnBrokenResource(jedis);
			}
			count++;
		} while (jedis == null && count < RedisConfig.retryNum);

		return jedis;
	}

	/**
	 * 释放redis实例到连接池.
	 * 
	 * @param jedis
	 *            redis实例
	 */
	public void closeJedis(Jedis jedis, String ip, int port) {
		if (jedis != null) {
			getPool(ip, port).returnResource(jedis);
		}
	}
	
	

}