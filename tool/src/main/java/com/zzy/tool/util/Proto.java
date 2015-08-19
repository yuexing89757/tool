package com.zzy.tool.util;

/**
 * protostuff序列化的包装类，用于包裹序列化对象
 * 
 * @author xingdongyang
 * 
 */
public class Proto {

	/**
	 * 包装类的构造器
	 * @param value 传入需要进行序列化的对象
	 */
	public Proto(Object value) {
		this.value = value;
	}

	/**
	 * 要进行序列化的值
	 */
	private Object value;

	/**
	 * 获得待序列化的对象
	 * 
	 * @return
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * 放入需要序列化的对象
	 * 
	 * @param value
	 */
	public void setValue(Object value) {
		this.value = value;
	}

}