package com.sx.btccoin.util.balance;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.sx.btccoin.util.FireCoinUtil;


public class FireCoinBalanceUtil {
	/**
	 * 根据密钥获取用户信息
	 * 与验证码
	 **/
	public static String getFireCoinAccountInfo(){	
		String result = "";
		//参数数组
//		Map<String,String> sArray = new HashMap<String, String>();
		String uri = "https://api.huobi.com/api.php";
	    try {
	    	HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);  
			client.getHttpConnectionManager().getParams().setSoTimeout(10000);
			//post请求
			
			PostMethod method = new PostMethod(uri);
			String s = System.currentTimeMillis()+"";
//			System.out.println("===============>"+s);
//			System.out.println("===============>"+s.substring(0, 10));
			/*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String dateTime = df.format(s);           
			System.out.println(df); */    
			String param = "access_key="+FireCoinUtil.ACCESS_KEY+"&created="+s.substring(0, 10)+"&method=get_account_info&secret_key="+FireCoinUtil.SECRET_KEY;
			NameValuePair[] data = {
					new NameValuePair("method", "get_account_info"),
					new NameValuePair("access_key", FireCoinUtil.ACCESS_KEY),
					new NameValuePair("created", s.substring(0, 10)),
					new NameValuePair("sign", createSign(param))
			};
//			System.out.println("JSON=====>"+s);
			method.setRequestBody(data);
			client.executeMethod(method);
			//返回json结果
			result = method.getResponseBodyAsString();
//			System.out.println("result======>"+result);
			//////////////////////////////////////////////////////////////////////////////////////////
			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			//////////////////////////////////////////////////////////////////////////////////////////
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
     * 生成32位大写MD5值
     */
    private static final char HEX_DIGIT[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};  
	public static String getMD5String(String str) {
		try {
			if(str==null || str.trim().length() == 0){
				return "";
			}
			byte[] bytes	=	str.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(bytes);
			bytes = messageDigest.digest();
			StringBuilder sb = new StringBuilder();  
	        for(int i = 0; i < bytes.length; i++){  
	            sb.append(HEX_DIGIT[(bytes[i] & 0xf0) >> 4] + "" + HEX_DIGIT[bytes[i] & 0xf]);  
	        }  
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	public static String createSign(String param){
		String s = getMD5String(param);
//		System.out.println("====>"+s);
		return s;
	}
	
	
}
