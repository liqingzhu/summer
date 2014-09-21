package com.sx.btccoin.util.balance;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sx.btccoin.util.BtcChinaUtil;
public class BtcChinaBalanceUtil {
	
//	private static  String ACCESS_KEY = "8336036d-e21c-447b-84b1-226acfd379f7";
//	private static  String SECRET_KEY = "18baf86c-d3e1-4d9b-b8e7-83c11fc3290d";

	private static  String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	private static Log log = LogFactory.getLog(BtcChinaBalanceUtil.class);
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
    public static String execCoreMethod(String methodName) throws Exception{
    	String info = "";    	
    	String tonce = ""+(System.currentTimeMillis() * 1000);
        String params = "tonce="+tonce.toString()+"&accesskey="+BtcChinaUtil.ACCESS_KEY+"&requestmethod=post&id=1&method="+methodName+"&params=";
//        log.error("BtcChinaUtil.SECRET_KEY==>"+BtcChinaUtil.SECRET_KEY);
        String hash = getSignature(params, BtcChinaUtil.SECRET_KEY);
        String url = "https://api.btcchina.com/api_trade_v1.php";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        String userpass = BtcChinaUtil.ACCESS_KEY + ":" + hash;
        String basicAuth = "Basic " + DatatypeConverter.printBase64Binary(userpass.getBytes());
        con.setRequestMethod("POST");
        con.setRequestProperty("Json-Rpc-Tonce", tonce.toString());
        con.setRequestProperty ("Authorization", basicAuth);
        String postdata = "{\"method\": \""+methodName+"\", \"params\": [], \"id\": 1}";
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postdata);
        wr.flush();
        wr.close();
//        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        info =  response.toString();
    	return info;
    }
    
    
    public static String getAccoutInfo() throws Exception{
    	String tonce = ""+(System.currentTimeMillis() * 1000);
        String params = "tonce="+tonce.toString()+"&accesskey="+BtcChinaUtil.ACCESS_KEY+"&requestmethod=post&id=1&method=getAccountInfo&params=";
        String hash = getSignature(params, BtcChinaUtil.SECRET_KEY);
        String url = "https://api.btcchina.com/api_trade_v1.php";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        String userpass = BtcChinaUtil.ACCESS_KEY + ":" + hash;
        String basicAuth = "Basic " + DatatypeConverter.printBase64Binary(userpass.getBytes());
        con.setRequestMethod("POST");
        con.setRequestProperty("Json-Rpc-Tonce", tonce.toString());
        con.setRequestProperty ("Authorization", basicAuth);
        String postdata = "{\"method\": \"getAccountInfo\", \"params\": [], \"id\": 1}";
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postdata);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
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
