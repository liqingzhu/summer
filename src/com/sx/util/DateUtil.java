package com.sx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	// 短日期格式
	public static String DATE_FORMAT = "yyyy-MM-dd";
	 
	// 长日期格式
	public static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	 
	// 长日期格式
	public static String TIME_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
	/**
	  * 将长整型数字转换为日期格式的字符串
	  * 
	  * @param time
	  * @param format
	  * @return
	  */
	 public static String convert2String(long time) {
		 String format = DateUtil.TIME_FORMAT;
		 SimpleDateFormat sf = new SimpleDateFormat(format);
		 if (time > 0l) {
		   Date date = new Date(time);
		   return sf.format(date);
		 }else{
		   Date date = new Date();
		   return sf.format(date);
		 }
	 }
	public static String makeTradeCodeId(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date();
		String str = sdf.format(date);
		return str;
	}
	
	public static String getToday(){
		String str = "";
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		str = date.format(new Date());
		return str;
	}
	/**
	 *获取时间编码 
	 **/
	public static String getTodayCode(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	/**
	 * 
	 * 将date 转换成yyyy-MM-dd HH:mm:ss
	 * 
	 * */
	public static String getToday(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 *根据参数返回
	 *当前年份+月份
	 *当前年份+周别 
	 *当前年份+月+日
	 **/
	public static String getDateName(String dayflag)
	{
		
		String str = "";
		if (dayflag.equals("month")) {
			//默认是返回当前月份
			str = "yyyy-MM";
			
		} else if(dayflag.equals("week")){
			//默认是返回当前周
			str = "yyyy-ww";
		}else {
			//默认是返回当前日期
			str = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(str);
		String date = sdf.format(new Date());
		return date;
	}
}
