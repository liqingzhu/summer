/**
 * 
 */
package com.sx.util;

/**
 * @author Administrator
 * 处理字符串的类
 */
public class StringUtil {
	public static String formatStringToHTML(String str){
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("\\\"", "'");
		str = str.replaceAll("\t", "");
		return str;
	}
}
