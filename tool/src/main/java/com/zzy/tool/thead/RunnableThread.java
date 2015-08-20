package com.zzy.tool.thead;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.io.Files;
import com.zzy.tool.activemq.ActiveMQProducter;
import com.zzy.tool.activemq.ActiveMQUtil;
import com.zzy.tool.bean.TaskBean;
import com.zzy.tool.dom4j.XmlFileTool;
import com.zzy.tool.enumtype.LanguageType;
import com.zzy.tool.util.SerializeUtil;
import com.zzy.tool.util.TranslateBean;

public class RunnableThread implements Runnable{
	private Map<String, String> map = null;
	private String pathAndFileName = null;

	public RunnableThread(Map<String, String> wordQueue,String pathAndFileName) {
		this.map = wordQueue;
		this.pathAndFileName = pathAndFileName;
	}
	
	public void run() {
		MQProduct(pathAndFileName,map);
	}

	
	
	/**
	 * writeToMq
	 * @param path
	 * @param map
	 */
	public void MQProduct(String pathAndFileName, Map<String, String> map) {
		System.out.println(pathAndFileName);
		String[] data = pathAndFileName.split(",");
		String srcTOdist = null; // 语言字符串
		String[] srcDist = null; // 语言数组
		if (data.length == 2) {
			srcTOdist = data[0];
			String fileName = data[1];   //文件路径和名字
			if (srcTOdist != null) {
				srcDist = srcTOdist.split("_");
			}
			if (srcTOdist != null && srcDist.length == 2) {
				// 读取文件生产者创建数据
				FileReader in = null;
				try {
					in = new FileReader(fileName);
					LineNumberReader reader = new LineNumberReader(in);
					String readLine = null;
					// 拼接queueName
					StringBuffer sb = new StringBuffer(srcDist[1]);
					sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
					String wordQueue = srcDist[0] + sb.toString() + "WordQueue";
					String tcp=map.get(wordQueue);
					
					if (tcp != null) {
						ActiveMQProducter activeMQProducter = ActiveMQUtil.getActiveMQProducter(tcp, wordQueue);
						while ((readLine = reader.readLine()) != null) {
							TranslateBean bean = new TranslateBean();
							bean.setSrcLANG(srcDist[0]);
							bean.setTargetLANG(srcDist[1]);
							bean.setKeyWord(readLine);
							activeMQProducter.sendMessage(SerializeUtil.jsonSerialize(bean));
							activeMQProducter.commit();
						}
						activeMQProducter.commitAndClose();
						in.close();
						reader.close();
						// 将读取后的文件进行迁移
						File file = new File(fileName);
						String parentPath = file.getParent();
						String backPath = parentPath + File.separator + "back";
						File backFile = new File(backPath);
						if (!backFile.exists()) {
							backFile.mkdirs();
						}
						File renameFile = new File(backPath + File.separator + file.getName());
						Files.move(file, renameFile);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
