package com.sx.btccoin.util.trade;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.alibaba.fastjson.JSONObject;
import com.sx.btccoin.util.BtcChinaUtil;
import com.sx.btccoin.util.balance.Verifier;
public class BtcChinaTradeUtil {
	private static String BTCCHINA_TRADE_MARKET_URL = "https://api.btcchina.com/api_trade_v1.php";
	private static  String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	private static Log log = LogFactory.getLog(BtcChinaTradeUtil.class);
	private static String BUY_METHOD_NAME = "buyOrder2";
	private static String SELL_METHOD_NAME = "sellOrder2";
	
	/**
	 * 交易BTC挂卖单
	 **/
	@SuppressWarnings("unchecked")
	public static HashMap<String,Object> makeSellOrderBtc(String price,String qty) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		try {
			String str = BtcChinaTradeUtil.execCoreMethod(BtcChinaTradeUtil.SELL_METHOD_NAME, price + ","+ qty);
			map = (HashMap<String,Object>)JSONObject.parseObject(str,HashMap.class);
		} catch (Exception e) {
			log.error("交易BTC挂买单",e);
		}
		return map;
	}
	/**
	 * 交易BTC挂买单
	 **/
	public static HashMap<String,Object> makeBuyOrderBtc(String price,String qty) throws Exception{
		HashMap<String,Object> map = new HashMap<String,Object>();
		try {
			String str = BtcChinaTradeUtil.execCoreMethod(BtcChinaTradeUtil.BUY_METHOD_NAME, price + ","+ qty);
			map = (HashMap<String,Object>)JSONObject.parseObject(str,HashMap.class);
		} catch (Exception e) {
			log.error("交易BTC挂买单",e);
		}
		return map;
	}
	
	public static String getSignature(String data,String key) throws Exception {
		 
        // get an hmac_sha1 key from the raw key bytes
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
 
        // get an hmac_sha1 Mac instance and initialize with the signing key
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
 
        // compute the hmac on input data bytes
        byte[] rawHmac = mac.doFinal(data.getBytes());
 
        return bytArrayToHex(rawHmac);  
    }
 
 
    private static String bytArrayToHex(byte[] a) {
       StringBuilder sb = new StringBuilder();
       for(byte b: a)
          sb.append(String.format("%02x", b&0xff));
       return sb.toString();
    }
    
    /**
	 * 公共核心方法名称
	 * @Param methodName
	 * @Param MethodName
	 * @Param MethodName
	 * @Param MethodName 
	 **/
    public static String execCoreMethod(String methodName,String param) throws Exception{
    	/*System.out.println("=====>"+param);
    	System.out.println("=====>"+methodName);*/	
    	 String tonce = ""+(System.currentTimeMillis() * 1000);
         //修改这里的method参数信息
//         String params = "tonce="+tonce.toString()+"&accesskey="+ACCESS_KEY+"&requestmethod=post&id=1&method=getAccountInfo&params=";  
    	 String params = "tonce="+tonce.toString()+"&accesskey="+BtcChinaUtil.ACCESS_KEY+"&requestmethod=post&id=1&method="+methodName+"&params="+param;  
         String hash = BtcChinaTradeUtil.getSignature(params, BtcChinaUtil.SECRET_KEY);  
//         String url = "https://api.btcchina.com/api_trade_v1.php";
         URL obj = new URL(BtcChinaTradeUtil.BTCCHINA_TRADE_MARKET_URL);
         HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
         String userpass = BtcChinaUtil.ACCESS_KEY + ":" + hash;
         String basicAuth = "Basic " + DatatypeConverter.printBase64Binary(userpass.getBytes());
         con.setHostnameVerifier(new Verifier());
         //add reuqest header
         con.setRequestMethod("POST");
         con.setRequestProperty("Json-Rpc-Tonce", tonce.toString());
         con.setRequestProperty ("Authorization", basicAuth);  
//         String postdata = "{\"method\": \"getAccountInfo\", \"params\": [], \"id\": 1}";
//         String postdata = "{"method":"getDeposits","params":["BTC"],"id":1}";
//         String postdata = "{\"method\":\"getDeposits\",\"params\":[\"BTC\"],\"id\":1}";
         String postdata = "{\"method\":\""+methodName+"\",\"params\":["+param+"],\"id\":1}";
//         String postdata = "{\"method\":\""+methodName+"\",\"params\":[],\"id\":1}";
         // Send post request
         con.setDoOutput(true);
         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
         wr.writeBytes(postdata);
         wr.flush();
         wr.close();  
         int responseCode = con.getResponseCode();
         /*System.out.println("\nSending 'POST' request to URL : " + url);
         System.out.println("Post parameters : " + postdata);
         System.out.println("Response Code : " + responseCode);  */
         BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
         String inputLine;
         StringBuffer response = new StringBuffer();  
         while ((inputLine = in.readLine()) != null) {
             response.append(inputLine);
         }
         in.close();
         //print result
//         System.out.println(response.toString());
    	 return response.toString();
    }
    /**
     *测试的Main方法 
     ***/
    public static void main(String args[]) throws Exception{
 
 
        String tonce = ""+(System.currentTimeMillis() * 1000);
 
        String params = "tonce="+tonce.toString()+"&accesskey="+BtcChinaUtil.ACCESS_KEY+"&requestmethod=post&id=1&method=getAccountInfo&params=";
 
        String hash = getSignature(params, BtcChinaUtil.SECRET_KEY);
 
        String url = "https://api.btcchina.com/api_trade_v1.php";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        String userpass = BtcChinaUtil.ACCESS_KEY + ":" + hash;
        String basicAuth = "Basic " + DatatypeConverter.printBase64Binary(userpass.getBytes());
 
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Json-Rpc-Tonce", tonce.toString());
        con.setRequestProperty ("Authorization", basicAuth);
 
        String postdata = "{\"method\": \"getAccountInfo\", \"params\": [], \"id\": 1}";
 
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postdata);
        wr.flush();
        wr.close();
 
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + postdata);
        System.out.println("Response Code : " + responseCode);
 
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
 
        //print result
        System.out.println(response.toString());
 
    }
}

