package com.zzy.tool.splitstr;

public class SplitTool {

	public static void main(String[] args) {
		    String line = "测试1\t测试2\t测试3\t测试4";
		    String[] lineArr = line.split("\\t");
		    System.out.println("从字符串读，行内容：" + line);
		    System.out.println("Arr.length: " + lineArr.length + "\tArr[0]: " + lineArr[0]);
	}

}
