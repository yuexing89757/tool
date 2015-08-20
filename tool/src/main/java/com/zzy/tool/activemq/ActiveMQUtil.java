package com.zzy.tool.activemq;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.log4j.Logger;

public class ActiveMQUtil {
	private static final Logger logger = Logger.getLogger(ActiveMQUtil.class);
	// 连接池 map 根据ip分池
	public static ConcurrentMap<String, PooledConnectionFactory> connectionFactoryMap = new ConcurrentHashMap<String, PooledConnectionFactory>();

	/**
	 * 获取连接池工厂
	 * 
	 * @param tcp
	 * @return
	 */
	public static PooledConnectionFactory getConnectionFactory(String tcp) {
		if (null != ActiveMQUtil.connectionFactoryMap.get(tcp)) {
			return ActiveMQUtil.connectionFactoryMap.get(tcp);
		} else {
			ActiveMQUtil.connectionFactoryMap.put(tcp, ActiveMQUtil.createConnectionFactory(tcp));
			return ActiveMQUtil.connectionFactoryMap.get(tcp);
		}
	}

	/**
	 * 发送列表数据到队列
	 * 
	 * @param tcp
	 * @param queueName
	 */
	public static ActiveMQProducter getActiveMQProducter(String tcp, String queueName) {
		Connection connection = null;
		Session session = null;
		Destination destination;
		MessageProducer producer;
		ActiveMQProducter activeMQProducter = null; // 自定义生产者
		try {
			// 创建一个连接
			connection = ActiveMQUtil.getConnectionFactory(tcp).createConnection();
			connection.start();
			// 创建一个会话
			session = connection.createSession(Boolean.TRUE.booleanValue(), Session.AUTO_ACKNOWLEDGE);
			// 创建一个队列
			destination = session.createQueue(queueName);
			// 创建生产者
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			activeMQProducter = new ActiveMQProducter(connection, session, producer);
		} catch (Exception e) {
			logger.error("【发送数据到队列】", e);
			e.printStackTrace();
			try {
				session.rollback();
			} catch (JMSException e1) {
				logger.error("【session 回滚失败】", e1);
				e1.printStackTrace();
			}
		}
		return activeMQProducter;
	}

	/**
	 * 接收数据
	 * 
	 * @param tcp
	 * @param queueName
	 */
	public static ActiveMQConsumer getActiveMQConsumer(String tcp, String queueName) {
		Connection connection = null;
		Session session;
		Destination destination;
		MessageConsumer consumer;
		ActiveMQConsumer activeMQConsumer = null;
		try {
			// 创建连接
			connection = ActiveMQUtil.getConnectionFactory(tcp).createConnection();
			connection.start();
			// 创建会话
			session = connection.createSession(Boolean.FALSE.booleanValue(), Session.AUTO_ACKNOWLEDGE);
			// 创建一个队列
			destination = session.createQueue(queueName);
			// 创建消费者
			consumer = session.createConsumer(destination);
			// 获取自定义消费者
			activeMQConsumer = new ActiveMQConsumer(connection, session, consumer);
		} catch (Exception e) {
			logger.error("【接收队列数据】", e);
			e.printStackTrace();
		}
		return activeMQConsumer;
	}

	private static PooledConnectionFactory createConnectionFactory(String tcp) {
		// 连接池工厂
		PooledConnectionFactory pooledConnectionFactory = null;

		// ConnectionFactory ：连接工厂，JMS 用它创建连接
		ActiveMQConnectionFactory connectionFactory = null;
		// 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, tcp);

		pooledConnectionFactory = new PooledConnectionFactory();
		pooledConnectionFactory.setConnectionFactory(connectionFactory);
		// 设置最大连接数
		pooledConnectionFactory.setMaxConnections(3000);
		// 设置最小
		pooledConnectionFactory.setMaximumActiveSessionPerConnection(200);
		// 后台对象清理时，休眠时间超过了3000毫秒的对象为过期    -1为不过期
		pooledConnectionFactory.setTimeBetweenExpirationCheckMillis(-1);

		return pooledConnectionFactory;
	}

}