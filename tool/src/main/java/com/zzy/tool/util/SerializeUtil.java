package com.zzy.tool.util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

public class SerializeUtil {
	
	/**
     * 序列化
     * 
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 反序列化
     * 
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {

        }
        return null;
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