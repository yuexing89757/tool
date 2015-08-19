package com.zzy.tool.util;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
/**
 * google的protoStuff框架的工具类
 * @author xingdongyang
 *
 */
public class ProtostuffSerialize{
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
}
