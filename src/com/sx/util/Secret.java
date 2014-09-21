package com.sx.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Secret {
	public static String getMD5(String srcTxt) throws NoSuchAlgorithmException,
			NullPointerException {
		if (srcTxt == null) {
			throw new java.lang.NullPointerException("输入参数为NULL!");
		}
		MessageDigest digest;
		// 定义调用的方法 为 MD5
		String algorithm = "MD5";
		// 结果字符串
		String result = "";
		// 初始化并计算
		digest = MessageDigest.getInstance(algorithm);
		digest.update(srcTxt.getBytes());
		byte[] byteRes = digest.digest();
		// 计算byte数组的长度
		int length = byteRes.length;
		// 将byte数组转换成字符串
		for (int i = 0; i < length; i++) {
			result = result + byteHEX(byteRes[i]);
		}
		// 返回加密的字符串
		return result;
	}

	/**
	 * 将byte类型转成 String 类型
	 * 
	 * @param ib
	 *            byte 输入 byte 类型
	 * @return String 返回 String 类型
	 */
	private static String byteHEX(byte ib) {
		char[] DigitNormal = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] ob = new char[2];
		ob[0] = DigitNormal[(ib >>> 4) & 0X0F];
		ob[1] = DigitNormal[ib & 0X0F];
		return new String(ob);
	}
}
