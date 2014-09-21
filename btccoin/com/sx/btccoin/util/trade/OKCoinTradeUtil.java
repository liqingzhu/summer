package com.sx.btccoin.util.trade;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.sx.btccoin.util.OkCoinUtil;

public class OKCoinTradeUtil {
	private static Log log = LogFactory.getLog(OKCoinTradeUtil.class);
	private static String BUY_METHOD_NAME = "buy";
	private static String SELL_METHOD_NAME = "sell";
	private static String TRADE_URL = "https://www.okcoin.com/api/trade.do";
	/**
	 * 下买订单交易
	 * */
	@SuppressWarnings("unchecked")
	public static HashMap<String,Object> makeBuyOrderBtc(String price,String qty){
		HashMap<String,Object> map = new HashMap<String,Object>();
		try {
			String str = OKCoinTradeUtil.tradeApi(OKCoinTradeUtil.BUY_METHOD_NAME, price,qty);
			map = (HashMap<String,Object>)JSONObject.parseObject(str,HashMap.class);
		} catch (Exception e) {
			log.error("OKCOIN 交易BTC挂买单",e);
		}
		return map;
	}
	/**
	 * 下卖订单交易
	 * */
	@SuppressWarnings("unchecked")
	public static HashMap<String,Object> makeSellOrderBtc(String price,String qty){
		HashMap<String,Object> map = new HashMap<String,Object>();
		try {
			String str = OKCoinTradeUtil.tradeApi(OKCoinTradeUtil.SELL_METHOD_NAME, price,qty);
			map = (HashMap<String,Object>)JSONObject.parseObject(str,HashMap.class);
		} catch (Exception e) {
			log.error("OKCOIN 交易BTC挂卖单",e);
		}
		return map;
	}
	/**
	 * 下订单交易
	 * */
	public static String tradeApi(String tradetype,String rate,String amount){
		String result = "";
		Map<String,String> map = new HashMap<String, String>();
		try {
			map.put("partner", OkCoinUtil.PARTNER);
			map.put("symbol", "btc_cny");
			/*map.put("type", "buy");*/
			/*map.put("rate", "9.91");
			map.put("amount", "1");*/
			map.put("type", tradetype);
			map.put("rate", rate);
			map.put("amount", amount);
			//对参数数组签名
			String sign = buildMysign(map, OkCoinUtil.SECRET_KEY);
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);  
			client.getHttpConnectionManager().getParams().setSoTimeout(10000);
			//post请求
			PostMethod method = new PostMethod(OKCoinTradeUtil.TRADE_URL);
			NameValuePair[] data = {
					new NameValuePair("partner", OkCoinUtil.PARTNER),
					new NameValuePair("symbol", "btc_cny"),
					new NameValuePair("type", tradetype),
					new NameValuePair("rate", rate),
					new NameValuePair("amount", amount),
					/*new NameValuePair("type", "buy"),
					new NameValuePair("rate", "9.91"),
					new NameValuePair("amount", "1"),*/
					new NameValuePair("sign", sign)
			};
			method.setRequestBody(data);
			client.executeMethod(method);
			result = method.getResponseBodyAsString();
			/*map.put("sign", "");*/
		} catch (Exception e) {
			log.error("OKCOIN下订单交易",e);
//			e.printStackTrace();
		}
		return result;
	}
    /**
     * 生成签名结果
     * @param sArray 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildMysign(Map<String, String> sArray,String secretKey) {
    	String mysign = "";
		try {
			String prestr = createLinkString(sArray); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
	        prestr = prestr + secretKey; //把拼接后的字符串再与安全校验码直接连接起来
	        mysign = getMD5String(prestr);
		} catch (Exception e) {
			log.error("OKCOIN下订单交易",e);
			e.printStackTrace();
		}
        return mysign;
    }
    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
//        System.out.println(prestr);
        return prestr;
    }
    /**
     * 生成32位大写MD5值
     */
    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};  
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
	            sb.append(HEX_DIGITS[(bytes[i] & 0xf0) >> 4] + "" + HEX_DIGITS[bytes[i] & 0xf]);  
	        }  
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			log.error("OKCOIN下订单交易",e);
		}
		return "";
	}
}
