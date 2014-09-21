package com.sx.util;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.JSONSerializerMap;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;

public class JSONUtils {
	/**
	 *利用fastjson jar包对集合进行转换
	 *这里只返回对应Class文件的类不考虑该类的父类情况 
	 **/
	public static String convertListToJson(List list){
		return JSONArray.toJSONString(list);
	}
	/***
	 *利用fastjson jar包对集合进行转换
	 *这里只返回对应Class文件的类不考虑该类的父类情况 
	 ***/
	@SuppressWarnings("unchecked")
	public static String convertListToJson(Class obj,List list,Integer total)
	{
		StringBuilder bulider = new StringBuilder();
		JSONSerializerMap mapping = new JSONSerializerMap();  
		JavaBeanSerializer javabean = new JavaBeanSerializer(obj);
		JSONSerializer serializer = new JSONSerializer(mapping);  
		serializer.write(list);  
		String jsonString = serializer.toString(); 
		bulider.append("{totalCount:");
		bulider.append(total.intValue()+"");
		bulider.append(",data:");
		bulider.append(jsonString);
		bulider.append("}");
		return  bulider.toString();
	}
	
	/**
	 *利用fastjson jar包对集合进行转换
	 *这里只返回对应Class文件的类不考虑该类的父类情况 
	 **/
	@SuppressWarnings("unchecked")
	public static String convertListToJson(Class obj,List list)
	{
		JSONSerializerMap mapping = new JSONSerializerMap();  
		JSONSerializer serializer = new JSONSerializer(mapping);  
		serializer.write(list);  
		String jsonString = serializer.toString(); 
		return jsonString;
	}
}
