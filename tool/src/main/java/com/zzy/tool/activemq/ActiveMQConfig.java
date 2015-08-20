package com.zzy.tool.activemq;

import com.zzy.tool.util.ConfigManager;


public class ActiveMQConfig {
	public static String tcp=(String) ConfigManager.getInstance().getConfigItem("mq.ip");  //ip地址
	public static String wordQueue=(String) ConfigManager.getInstance().getConfigItem("mq.wordQueue");  //wordQueue
	public static String resultQueue=(String) ConfigManager.getInstance().getConfigItem("mq.resultQueue");   //resultQueue
	public static String wordNullQueue=(String) ConfigManager.getInstance().getConfigItem("mq.wordNullQueue");   //wordNullQueue
	public static Long getWordTimeOut=Long.parseLong((String) ConfigManager.getInstance().getConfigItem("mq.getWordTimeOut"));   //设置接收者接收消息的超时时间
	
}
