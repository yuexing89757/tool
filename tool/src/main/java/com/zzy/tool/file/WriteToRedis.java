package com.zzy.tool.file;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import redis.clients.jedis.Jedis;

import com.google.common.io.Files;
import com.zzy.tool.redis.JedisUtil;
import com.zzy.tool.util.SerializeUtil;
import com.zzy.tool.util.TranslateBean;

public class WriteToRedis {

	public static void main(String[] args) {
		String fullPath = "D:\\lang";
		File file = new File(fullPath);
		if (file.exists()) {
			File[] files = file.listFiles();
			for (File singleFile : files) {
				if (!singleFile.isDirectory()) {
					String fileName = singleFile.getPath();
					fileName = fileName.replaceAll("\\\\", "/");
					try {
						openTread(fileName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private static ExecutorService executor = Executors.newFixedThreadPool(16);


	public static void test() {
		
	}

	public static void openTread(String fileName) {
		System.out.println(fileName);
		
		MQProduct(fileName);
	}

	// 通过线程获取queue数据
	class QueueThread implements Runnable {
		String fileName = null;

		public QueueThread() {
			super();
		}

		public QueueThread(String fileName) {
			super();
			this.fileName = fileName;
		}

		public void run() {
			MQProduct(fileName);
		}

	}

	public static void MQProduct(String fileName) {
		Jedis jedis=JedisUtil.getInstance().getJedis("172.16.1.210",6379);
		// 读取文件生产者创建数据
		FileReader in = null;
		try {
			in = new FileReader(fileName);
			LineNumberReader reader = new LineNumberReader(in);
			String readLine = null;

			while ((readLine = reader.readLine()) != null) {
				String[] Strings = readLine.split("\t");
				if (Strings.length != 2) {
					System.out.println("跳过");
				} else {
					try{
					System.out.println(fileName+"____"+Strings[0] + "___" + Strings[1]);
					TranslateBean translateBean=new TranslateBean();
					translateBean.setSrcLANG("zh");
					translateBean.setTargetLANG("en");
					translateBean.setSrcLine(Strings[0]);
					translateBean.setTargetLine(Strings[1]);
					///if(!jedis.exists(Strings[0] + Strings[1])){
					jedis.set(Strings[0] + Strings[1], SerializeUtil.jsonSerialize(translateBean));
					//}
					
					}catch(Exception e){
						e.printStackTrace();
					}
					  
				}
			}
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

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
