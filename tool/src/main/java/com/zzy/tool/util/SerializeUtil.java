package com.zzy.tool.util;
import com.alibaba.fastjson.JSON;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.google.gson.Gson;

public class SerializeUtil {
	
	static Schema<Proto> schema = (Schema<Proto>) RuntimeSchema.getSchema(Proto.class);
	public static  byte[] Object2Byte(Object obj){
		return Object2Byte(obj,1024);
	}
	public static  byte[] Object2Byte(Object obj, int size){
		Proto proto = new Proto(obj);
		LinkedBuffer buffer = LinkedBuffer.allocate(size);
		byte[] buf = ProtostuffIOUtil.toByteArray(proto, schema, buffer);
		return buf;
	}
	public static  Object Byte2Object(byte[] buf){
		Proto proto = schema.newMessage();
		ProtostuffIOUtil.mergeFrom(buf, proto, schema);
		return proto.getValue();
	}
	
	

	/**
	 * 转换json字符串 JSON也是一种序列化。
	 * @param object
	 * @return
	 */
	public static String jsonSerialize(Object object) {
	     return new Gson().toJson(object);
	}
	
	/**
	 * 字符串转对象
	 * @param object
	 * @param classOfT
	 * @return
	 */
	public static <T> T unJsonSerialize(String object,Class<T> classOfT) {
    	T t = null;
    	t=new Gson().fromJson(object, classOfT);
		return t;	
	}
	
	

	/**
	 * 转换json字符串 JSON也是一种序列化。
	 * @param object
	 * @return
	 */
	public static String fastJsonSerialize(Object object) {
	     return JSON.toJSONString(object);
	}
	
	/**
	 * 字符串转对象
	 * @param object
	 * @param classOfT
	 * @return
	 */
	public static <T> T unFastJsonSerialize(String object,Class<T> classOfT) {
		T t = null;
        try {
            t = JSON.parseObject(object, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
	}

	
	
}