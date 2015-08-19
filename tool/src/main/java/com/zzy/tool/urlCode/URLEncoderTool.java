package com.zzy.tool.urlCode;

import java.io.UnsupportedEncodingException;

public class URLEncoderTool {

	public static void main(String[] args) {

		try {
			String mytext = java.net.URLEncoder.encode("简体中文", "utf-8");
			System.out.println("1:"+mytext);
			
			String mytext2 = java.net.URLDecoder.decode("http://mymemory.translated.net/zh/%E7%AE%80%E4%BD%93%E4%B8%AD%E6%96%87/%E8%8B%B1%E8%AF%AD/天使", "utf-8");
			System.out.println("2:"+mytext2);
			
			String zhongguo=new String("zhongguo".getBytes("iso8859_1")); 
			System.out.println("3:"+zhongguo);
			
			zhongguo=java.net.URLDecoder.decode(zhongguo,"utf-8");			
			System.out.println("4:"+zhongguo);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
