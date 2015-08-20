package com.zzy.tool.activemq;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

public class ActiveMQProducter {
	private static final Logger logger = Logger.getLogger(ActiveMQProducter.class);

	Session session = null;
	MessageProducer producer;
	Connection connection = null;

	public ActiveMQProducter(Connection connection, Session session,
			MessageProducer producer) {
		super();
		this.connection = connection;
		this.session = session;
		this.producer = producer;
	}

	/**
	 * 发送消息到队列
	 * 
	 * @param word
	 */
	public void sendMessage(String word) {
		try {
			TextMessage message = this.session.createTextMessage(word);
			this.producer.send(message);
		} catch (Exception e) {
			logger.error("【存入队列失败】:[" + word + "]",e);
		}

	}

	/**
	 * 关闭session和链接
	 */
	public void commitAndClose() {
		try {
			this.session.commit();
		} catch (JMSException e) {
			logger.error("【整体存入队列失败】",e);
		} finally {
			try {
				if (null != session)
					session.close();
				if (null != connection)
					connection.close();
			} catch (Throwable ignore) {
				logger.error("【关闭连接队列数据】", ignore);
			}
		}
	}

	/**
	 * 关闭session和链接
	 */
	public void commit() {
		try {
			this.session.commit();
		} catch (JMSException e) {
			logger.error("【整体存入队列失败】",e);
		}
	}

	/**
	 * 关闭链接
	 */
	public void close() {
		try {
			if (null != session)
				session.close();
			if (null != connection)
				connection.close();
		} catch (Throwable ignore) {
			logger.error("【关闭连接队列数据】", ignore);
		}
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public MessageProducer getProducer() {
		return producer;
	}

	public void setProducer(MessageProducer producer) {
		this.producer = producer;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
