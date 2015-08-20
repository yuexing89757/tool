package com.zzy.tool.activemq;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

public class ActiveMQConsumer {
	private static final Logger logger = Logger
			.getLogger(ActiveMQProducter.class);

	private Session session = null;
	private MessageConsumer consumer;
	private Connection connection = null;

	public ActiveMQConsumer(Connection connection, Session session,
			MessageConsumer consumer) {
		super();
		this.connection = connection;
		this.session = session;
		this.consumer = consumer;
	}
	/**
	 * 接收消息
	 * @return
	 */
	public String getMessage() {
		try {
			TextMessage message = (TextMessage) consumer.receive(ActiveMQConfig.getWordTimeOut);
			if (null != message) {
				// System.out.println("收到消息" + message.getText());
				return message.getText();
			} else {
				//logger.debug("【队列当前无数据数据】");
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return null;
	}
    
	/**
	 * 关闭链接
	 */
	public void close() {
		try {
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

	public MessageConsumer getConsumer() {
		return consumer;
	}

	public void setConsumer(MessageConsumer consumer) {
		this.consumer = consumer;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
