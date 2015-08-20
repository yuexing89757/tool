package com.zzy.tool.file;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.google.common.io.Files;
import com.zzy.tool.activemq.ActiveMQProducter;
import com.zzy.tool.activemq.ActiveMQUtil;
import com.zzy.tool.util.SerializeUtil;
import com.zzy.tool.util.TranslateBean;

public class WriteToActiveMq {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void MQProduct(String path, Map<String, String> map) {
		System.out.println(path);
		String[] data = path.split(",");
		String srcTOdist = null; // 语言字符串
		String[] srcDist = null; // 语言数组
		if (data.length == 2) {
			srcTOdist = data[0];
			path = data[1];
			if (srcTOdist != null) {
				srcDist = srcTOdist.split("_");
			}
			if (srcTOdist != null && srcDist.length == 2) {
				// 读取文件生产者创建数据
				FileReader in = null;
				try {
					in = new FileReader(path);
					LineNumberReader reader = new LineNumberReader(in);
					String readLine = null;
					// 拼接queueName
					StringBuffer sb = new StringBuffer(srcDist[1]);
					sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
					String wordQueue = srcDist[0] + sb.toString() + "WordQueue";
					String tcp = map.get(wordQueue);

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
						File file = new File(path);
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

	// 测试
	@Test
	public void test() {
		Map<String, String> map = null;
		String path = "zh_ara,D:/2/zh/ara/999_ALL.txt";
		try {
			map = new HashMap<String, String>();
			map.put("zhAraWordQueue", "tcp://172.16.6.87:61616?trace=true");
			MQProduct(path, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
