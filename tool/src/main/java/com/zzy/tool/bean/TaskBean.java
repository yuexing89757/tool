package com.zzy.tool.bean;

import java.util.List;
import java.util.Map;

public class TaskBean {

	public TaskBean() {
		
	}
	private String function;
	private String wordQueue;
	private String resultQueue;
	private String wordNullQueue;
	private String redisIpPort;
	private String converLNG;
	private List<Map<String,String>> listServerMap;
	
	
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
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
	public String getConverLNG() {
		return converLNG;
	}
	public void setConverLNG(String converLNG) {
		this.converLNG = converLNG;
	}
	public List<Map<String, String>> getListServerMap() {
		return listServerMap;
	}
	public void setListServerMap(List<Map<String, String>> listServerMap) {
		this.listServerMap = listServerMap;
	}
	public String getWordNullQueue() {
		return wordNullQueue;
	}
	public void setWordNullQueue(String wordNullQueue) {
		this.wordNullQueue = wordNullQueue;
	}
	public String getRedisIpPort() {
		return redisIpPort;
	}
	public void setRedisIpPort(String redisIpPort) {
		this.redisIpPort = redisIpPort;
	}
	
}
