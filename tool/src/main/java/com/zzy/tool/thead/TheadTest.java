package com.zzy.tool.thead;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.zzy.tool.bean.TaskBean;
import com.zzy.tool.dom4j.XmlFileTool;
import com.zzy.tool.enumtype.LanguageType;
import com.zzy.tool.util.ConfigManager;

public class TheadTest {

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(5);

		Map<String, String> queueMap = loadParam();

		List<String> fileList = readFile(ConfigManager.getInstance().getConfigItem("mq.file.path").toString());

		for (String file : fileList) {
			pool.submit(new RunnableThread(queueMap, file));
		}

		System.out.println("end");
	}

	@Test
	public void testjunit() {
		ExecutorService pool = Executors.newFixedThreadPool(5);

		Map<String, String> queueMap = loadParam();

		List<String> fileList = readFile(ConfigManager.getInstance().getConfigItem("mq.file.path").toString());

		for (String file : fileList) {
			pool.submit(new RunnableThread(queueMap, file));
		}

		System.out.println("end");
	}

	/**
	 * queuename + ip
	 * 
	 * @return
	 */
	public static Map<String, String> loadParam() {
		XmlFileTool.initTaskMap();
		Map<String, String> queueTcpName = new HashMap<String, String>(); // queue
																			// 名称+
																			// ip
		for (Map.Entry<String, TaskBean> entry : XmlFileTool.taskMap.entrySet()) {
			TaskBean taskBean = entry.getValue();
			if (Boolean.valueOf(taskBean.getFunction())) {
				String[] queue = taskBean.getWordQueue().split("_");
				queueTcpName.put(queue[0], queue[1]);
			}
		}
		return queueTcpName;
	}

	/**
	 * 返会 语言和 _ 文件名字
	 * 
	 * @param path
	 * @param wordQueue
	 * @return
	 */
	public static List<String> readFile(String basePath) {
		List<String> queueNamePath = new ArrayList<String>();
		LanguageType[] types = LanguageType.values();
		for (LanguageType languageType : types) {
			for (LanguageType languageType2 : types) {
				if (languageType == languageType2) {
					continue;
				}
				String srcTOdist = languageType + "_" + languageType2;
				String fullPath = basePath + "" + File.separator + languageType + File.separator + languageType2;
				File file = new File(fullPath);
				if (file.exists()) {
					File[] files = file.listFiles();
					for (File singleFile : files) {
						if (!singleFile.isDirectory()) {
							String fileName = singleFile.getPath();
							fileName = fileName.replaceAll("\\\\", "/");
							try {
								queueNamePath.add(srcTOdist + "," + fileName);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return queueNamePath;
	}

}
