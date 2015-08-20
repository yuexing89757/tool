package com.zzy.tool.bean;

public class ServerConfigBean {
	private String serverName;   //youdao    sendbase
	private String mqtcp;        
	private String redisIpPort;  //ip_port
	private String wordQueue;       // wordq_resltQ_wordNullQ
	private String resultQueue;       // wordq_resltQ_wordNullQ
	private String wordNullQueue;       // wordq_resltQ_wordNullQ
	private String srcTargetLANG;
	
	
	private String analyzeType;
	private int timeout;
	private String urlFont;
	private String urlEnd;
	private String baseDom;
	private String dataDom;
	
	public String getUrlEnd() {
		return urlEnd;
	}
	public void setUrlEnd(String urlEnd) {
		this.urlEnd = urlEnd;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getMqtcp() {
		return mqtcp;
	}
	public void setMqtcp(String mqtcp) {
		this.mqtcp = mqtcp;
	}
	public String getRedisIpPort() {
		return redisIpPort;
	}
	public void setRedisIpPort(String redisIpPort) {
		this.redisIpPort = redisIpPort;
	}

	public String getWordQueue() {
		return wordQueue;
	}
	public void setWordQueue(String wordQueue) {
		this.wordQueue = wordQueue;
	}
	public String getResultQueue() {
		return resultQueue;
	}
	public void setResultQueue(String resultQueue) {
		this.resultQueue = resultQueue;
	}
	public String getWordNullQueue() {
		return wordNullQueue;
	}
	public void setWordNullQueue(String wordNullQueue) {
		this.wordNullQueue = wordNullQueue;
	}
	public String getSrcTargetLANG() {
		return srcTargetLANG;
	}
	public void setSrcTargetLANG(String srcTargetLANG) {
		this.srcTargetLANG = srcTargetLANG;
	}
	public String getAnalyzeType() {
		return analyzeType;
	}
	public void setAnalyzeType(String analyzeType) {
		this.analyzeType = analyzeType;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public String getUrlFont() {
		return urlFont;
	}
	public void setUrlFont(String urlFont) {
		this.urlFont = urlFont;
	}
	public String getBaseDom() {
		return baseDom;
	}
	public void setBaseDom(String baseDom) {
		this.baseDom = baseDom;
	}
	public String getDataDom() {
		return dataDom;
	}
	public void setDataDom(String dataDom) {
		this.dataDom = dataDom;
	}
	

	

	
}
