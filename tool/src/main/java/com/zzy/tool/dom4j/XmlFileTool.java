package com.zzy.tool.dom4j;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import com.zzy.tool.bean.ServerConfigBean;
import com.zzy.tool.bean.TaskBean;


public class XmlFileTool {
    public static Map<String,TaskBean> taskMap=new HashMap<String,TaskBean>();
    public static ConcurrentMap<String, ServerConfigBean> paramServerMap = new ConcurrentHashMap<String, ServerConfigBean>();

	@SuppressWarnings("unchecked")
	@Test
	public static void parsefileQueueServerConfig() {
		String XML_PATH = XmlFileTool.class.getClassLoader().getResource("").getPath() + "queueServerConfig.xml"; // 配置文件路径
		List<Map<String,String>> listServerMap=null;
		try {
			// 创建SAXReader读取器，专门用于读取xml
			SAXReader saxReader = new SAXReader();
			// 根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取
			Document document = saxReader.read(new File(XML_PATH));
			Element rootElement = document.getRootElement();// 获取根节点
			Iterator<Element> modulesIterator = rootElement.elements("task").iterator(); // 根节点下面的对象
			// .getName()获取标签名 .getText()获取内容
			while (modulesIterator.hasNext()) {
				TaskBean taskBean=new TaskBean();
				Element moduleElement = modulesIterator.next();
				Element function = moduleElement.element("function");
				System.out.println(function.getName() + ":" + function.getText());
				Element wordQueue = moduleElement.element("wordQueue");
				System.out.println(wordQueue.getName() + ":" + wordQueue.getText());
				Element resultQueue = moduleElement.element("resultQueue");
				System.out.println(resultQueue.getName() + ":"+ resultQueue.getText());
				Element wordNullQueue = moduleElement.element("wordNullQueue");
				System.out.println(wordNullQueue.getName() + ":"+ wordNullQueue.getText());
				Element redisIpPort = moduleElement.element("redisIpPort");
				System.out.println(redisIpPort.getName() + ":"+ redisIpPort.getText());
				Element converLNG = moduleElement.element("converLNG");
				System.out.println(converLNG.getName() + ":" + converLNG.getText());
				Element serversElement = moduleElement.element("servers");
				Iterator<Element> serversIterator = serversElement.elements("server").iterator(); // 根节点下面的对象
				listServerMap=new ArrayList<Map<String,String>>();
				while (serversIterator.hasNext()) {
					Element serverElement = serversIterator.next();
					Element serverName = serverElement.element("serverName");
					System.out.println(serverName.getName() + ":" + serverName.getText());
					Element theadNum = serverElement.element("theadNum");
					System.out.println(theadNum.getName() + ":" + theadNum.getText());
					Element enable = serverElement.element("enable");
					System.out.println(enable.getName() + ":" + enable.getText());
					Map<String,String> serverMap=new HashMap<String,String>();
					serverMap.put(serverName.getName(), serverName.getText());
					serverMap.put(theadNum.getName(), theadNum.getText());
					serverMap.put(enable.getName(), enable.getText());
					listServerMap.add(serverMap);
				}
				taskBean.setWordQueue(wordQueue.getText());
				taskBean.setResultQueue(resultQueue.getText());
				taskBean.setWordNullQueue(wordNullQueue.getText());
				taskBean.setRedisIpPort(redisIpPort.getText());
				taskBean.setConverLNG(wordQueue.getText());
				taskBean.setListServerMap(listServerMap);
				taskBean.setFunction(function.getTextTrim());
				taskMap.put(converLNG.getText(),taskBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public static void parsefileDataServerConfig() {
		String XML_PATH = XmlFileTool.class.getClassLoader().getResource("").getPath() + "dataServerConfig.xml"; // 配置文件路径
		try {
			// 创建SAXReader读取器，专门用于读取xml
			SAXReader saxReader = new SAXReader();
			// 根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取
			Document document = saxReader.read(new File(XML_PATH)); 
			Element rootElement = document.getRootElement();//获取根节点
			
			//根节点下面的对象
			Iterator<Element> modulesIterator = rootElement.elements("dataServer").iterator(); 

			//.getName()获取标签名     .getText()获取内容
			while (modulesIterator.hasNext()) {
				Element moduleElement = modulesIterator.next();
				Element serverName = moduleElement.element("serverName");
				System.out.println(serverName.getName() + ":"+ serverName.getText());  
				
				Element analyzeType = moduleElement.element("analyzeType");
				System.out.println(analyzeType.getName() + ":"+ analyzeType.getText());
				
				Element timeout = moduleElement.element("timeout");
				System.out.println(timeout.getName() + ":"+ timeout.getText());
				
				Element urlFont = moduleElement.element("urlFont");
				System.out.println(urlFont.getName() + ":"+ urlFont.getText());
				
				Element urlEnd = moduleElement.element("urlEnd");
				System.out.println(urlEnd.getName() + ":"+ urlEnd.getText());
				
				Element baseDom = moduleElement.element("baseDom");
				System.out.println(baseDom.getName() + ":"+ baseDom.getText());
				
				Element dataDom = moduleElement.element("dataDom");
				System.out.println(dataDom.getName() + ":"+ dataDom.getText());
				
				ServerConfigBean serverConfig=new ServerConfigBean();
				serverConfig.setServerName(serverName.getText());
				serverConfig.setAnalyzeType(analyzeType.getText());
				serverConfig.setUrlFont(urlFont.getText());
				serverConfig.setUrlEnd(urlEnd.getText());
				serverConfig.setBaseDom(baseDom.getText());
				serverConfig.setDataDom(dataDom.getText());
				serverConfig.setTimeout(Integer.parseInt(timeout.getTextTrim()));
				paramServerMap.put(serverName.getText(), serverConfig);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取配置文件
	 * @return
	 */
	public static synchronized void  initTaskMap(){
		if(taskMap.isEmpty()){
			XmlFileTool.parsefileQueueServerConfig();
		}
	}
	
	/**
	 * 获取serverConfigMap
	 */
	public static synchronized void  initParamServerMap(){
		if(paramServerMap.isEmpty()){
			XmlFileTool.parsefileDataServerConfig();
		}
	}
}
