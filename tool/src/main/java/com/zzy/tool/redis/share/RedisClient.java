package com.zzy.tool.redis.share;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.zzy.tool.redis.JedisUtil;
import com.zzy.tool.redis.RedisConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClient {

	private Jedis jedis;// 非切片额客户端连接
	private JedisPool jedisPool;// 非切片连接池
	private ShardedJedis shardedJedis;// 切片额客户端连接
	private ShardedJedisPool shardedJedisPool;// 切片连接池

	public RedisClient() {
		initialPool();
		initialShardedPool();
		shardedJedis = shardedJedisPool.getResource();
		jedis = jedisPool.getResource();

	}

	/**
	 * 初始化非切片池
	 */
	private void initialPool() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		// 最大连接数, 默认10个
		config.setMaxTotal(RedisConfig.maxTotal);
		// 最大空闲连接数, 默认10个
		config.setMaxIdle(RedisConfig.maxIdle);
		// 最小连接数, 默认10个
		config.setMinIdle(RedisConfig.minIdle);
		// 最大等待时间
		config.setMaxWaitMillis(RedisConfig.maxWait);
		// 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
		config.setMaxWaitMillis(RedisConfig.maxWait);
		// 在获取连接的时候检查connection有效性, 默认false
		config.setTestOnBorrow(RedisConfig.testOnBorrow);
		// 在进行returnObject对返回的connection进行validateObject校验
		config.setTestOnReturn(RedisConfig.testOnBorrow);
		// 在空闲时检查连接池有效性, 默认false
		config.setTestWhileIdle(RedisConfig.testWhileIdle);

		// config.setTestOnBorrow(false);

		jedisPool = new JedisPool(config, "127.0.0.1", 6379);
	}

	/**
	 * 初始化切片池
	 */
	private void initialShardedPool() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		// 最大连接数, 默认10个
		config.setMaxTotal(RedisConfig.maxTotal);
		// 最大空闲连接数, 默认10个
		config.setMaxIdle(RedisConfig.maxIdle);
		// 最小连接数, 默认10个
		config.setMinIdle(RedisConfig.minIdle);
		// 最大等待时间
		config.setMaxWaitMillis(RedisConfig.maxWait);
		// 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
		config.setMaxWaitMillis(RedisConfig.maxWait);
		// 在获取连接的时候检查connection有效性, 默认false
		config.setTestOnBorrow(RedisConfig.testOnBorrow);
		// 在进行returnObject对返回的connection进行validateObject校验
		config.setTestOnReturn(RedisConfig.testOnBorrow);
		// 在空闲时检查连接池有效性, 默认false
		config.setTestWhileIdle(RedisConfig.testWhileIdle);

		// config.setTestOnBorrow(false);
		// slave链接
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));

		// 构造池
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}

	
	@Test
	public void show() {
		setOperate();
		jedisPool.returnResource(jedis);
		shardedJedisPool.returnResource(shardedJedis);
	}

	private void setOperate() {
	///	Jedis jedis = JedisUtil.getInstance().getJedis("172.16.1.210", 6379);
		System.out
				.println("======================set==========================");
		// 清空数据
		// System.out.println("清空库中所有数据："+jedis.flushDB());

		System.out.println("=============增=============");
		System.out.println("向sets集合中加入元素element001："
				+ jedis.sadd("sets", "element001"));
		System.out.println("向sets集合中加入元素element002："
				+ jedis.sadd("sets", "element002"));
		System.out.println("向sets集合中加入元素element003："
				+ jedis.sadd("sets", "element003"));
		System.out.println("向sets集合中加入元素element004："
				+ jedis.sadd("sets", "element004"));
		System.out.println("查看sets集合中的所有元素:" + jedis.smembers("sets"));
		System.out.println();

		System.out.println("=============删=============");
		System.out.println("集合sets中删除元素element003："
				+ jedis.srem("sets", "element003"));
		System.out.println("查看sets集合中的所有元素:" + jedis.smembers("sets"));
		/*
		 * System.out.println("sets集合中任意位置的元素出栈："+jedis.spop("sets"));//注：
		 * 出栈元素位置居然不定？--无实际意义
		 * System.out.println("查看sets集合中的所有元素:"+jedis.smembers("sets"));
		 */
		System.out.println();

		System.out.println("=============改=============");
		System.out.println();

		System.out.println("=============查=============");
		System.out.println("判断element001是否在集合sets中："
				+ jedis.sismember("sets", "element001"));
		System.out.println("循环查询获取sets中的每个元素：");
		Set<String> set = jedis.smembers("sets");
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			System.out.println(obj);
		}
		System.out.println();

		System.out.println("=============集合运算=============");
		System.out.println("sets1中添加元素element001："
				+ jedis.sadd("sets1", "element001"));
		System.out.println("sets1中添加元素element002："
				+ jedis.sadd("sets1", "element002"));
		System.out.println("sets1中添加元素element003："
				+ jedis.sadd("sets1", "element003"));
		System.out.println("sets1中添加元素element002："
				+ jedis.sadd("sets2", "element002"));
		System.out.println("sets1中添加元素element003："
				+ jedis.sadd("sets2", "element003"));
		System.out.println("sets1中添加元素element004："
				+ jedis.sadd("sets2", "element004"));
		System.out.println("查看sets1集合中的所有元素:" + jedis.smembers("sets1"));
		System.out.println("查看sets2集合中的所有元素:" + jedis.smembers("sets2"));
		System.out.println("sets1和sets2交集：" + jedis.sinter("sets1", "sets2"));
		System.out.println("sets1和sets2并集：" + jedis.sunion("sets1", "sets2"));
		System.out.println("sets1和sets2差集：" + jedis.sdiff("sets1", "sets2"));// 差集：set1中有，set2中没有的元素

	}

}