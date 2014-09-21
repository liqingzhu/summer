package com.sx.btccoin.util.trade;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.sx.btccoin.util.FireCoinUtil;


public class FireCoinTradeUtil {
	private static String BUY_METHOD_NAME = "buy";
	private static String SELL_METHOD_NAME = "sell";
	private static String TRADE_URL =  "https://api.huobi.com/api.php";
	private static Log log = LogFactory.getLog(FireCoinTradeUtil.class);
	/**
	 * 交易BTC挂卖单
	 **/
	@SuppressWarnings("unchecked")
	public static HashMap<String,Object> makeSellOrderBtc(String price,String qty) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		try {
			String str = FireCoinTradeUtil.tradeFireCoin(FireCoinTradeUtil.SELL_METHOD_NAME, price, qty);
			map = (HashMap<String,Object>)JSONObject.parseObject(str,HashMap.class);
		} catch (Exception e) {
			log.error("FireCoin 交易BTC挂买单",e);
		}
		return map;
	}
	/**
	 * 交易BTC挂买单
	 **/
	@SuppressWarnings("unchecked")
	public static HashMap<String,Object> makeBuyOrderBtc(String price,String qty) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		try {
			String str = FireCoinTradeUtil.tradeFireCoin(FireCoinTradeUtil.BUY_METHOD_NAME, price , qty);
			map = (HashMap<String,Object>)JSONObject.parseObject(str,HashMap.class);
		} catch (Exception e) {
			log.error("FireCoin 交易BTC挂买单",e);
		}
		return map;
	}
	
	public static String tradeFireCoin(String methodname,String price,String qty){
		String result = "";
		//参数数组
//		Map<String,String> sArray = new HashMap<String, String>();
//		String uri = "https://api.huobi.com/api.php";
	    try {
	    	HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);  
			client.getHttpConnectionManager().getParams().setSoTimeout(10000);
			//post请求
			String option = methodname;
			
			PostMethod method = new PostMethod(TRADE_URL);
			String s = System.currentTimeMillis()+"";
			/*System.out.println("火币网转换前==="+bcte.getPrice().toString());*/
//			double price = bcte.getPrice().doubleValue();
			/*System.out.println("火币网转换后==="+price);*/
//			double qty = bcte.getQty().doubleValue();
//			double price = 30000.0;
//			double qty = 100.0;
			String param = "access_key="+FireCoinUtil.ACCESS_KEY+"&amount="+qty+"&created="+s.substring(0, 10)+"&method="+option+"&price="+price+"&secret_key="+FireCoinUtil.SECRET_KEY;
//			String param = "access_key="+ACCESS_KEY+"&created="+s.substring(0, 10)+"&method=get_account_info&secret_key="+SECRET_KEY;
			NameValuePair[] data = {
					new NameValuePair("method", option),
					new NameValuePair("access_key", FireCoinUtil.ACCESS_KEY),
					new NameValuePair("price", price+""),
					new NameValuePair("amount", qty+""),
					new NameValuePair("created", s.substring(0, 10)),
					new NameValuePair("sign", createSign(param))
			};
			/*System.out.println("=====>"+s);*/
			method.setRequestBody(data);
			client.executeMethod(method);
			//返回json结果
			result = method.getResponseBodyAsString();
			/*System.out.println("======>"+result);*/
		} catch (HttpException e) {
//			e.printStackTrace();
			log.error("FireCoin 交易失败",e);
		} catch (IOException e) {
//			e.printStackTrace();
			log.error("FireCoin 交易失败",e);
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
			log.error("FireCoin 交易失败",e);
		}
		return "";
	}
	public static String createSign(String param){
		String s = getMD5String(param);
//		System.out.println("====>"+s);
		return s;
	}
	
}
