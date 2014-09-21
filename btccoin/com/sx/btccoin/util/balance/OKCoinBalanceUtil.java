package com.sx.btccoin.util.balance;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.sx.btccoin.okcoin.entity.OKCoinMarketDepthEntity;
import com.sx.btccoin.util.OkCoinUtil;
public class OKCoinBalanceUtil {
	//api接口地址
	private final static String USER_INFO_URI = "https://www.okcoin.cn/api/userinfo.do";
	private final static String OKCOIN_DEPTH = "https://www.okcoin.cn/api/depth.do";
	/*//密钥
	private final static String SECRET_KEY = "EB5EC45519B86DE1D5E55DC80164FA93";
	//合作者ID
	private final static long PARTNER = 3340190;*/
	private static Log log = LogFactory.getLog(OKCoinBalanceUtil.class);
	/**
	 * 获取OKCoin的交易深度数据
	 **/
	public static OKCoinMarketDepthEntity getOKCoinMarketDepthEntity(){
		OKCoinMarketDepthEntity mde = new OKCoinMarketDepthEntity();
		try {
			String str = OKCoinBalanceUtil.getInfos(OKCoinBalanceUtil.OKCOIN_DEPTH, null, "UTF8", true);
			
			mde = JSONObject.parseObject(str,OKCoinMarketDepthEntity.class);
		} catch (Exception e) {
			log.error("获取OKCOIN交易深度数据信息!",e);
		}
		return mde;
	}
	/**
	 * 执行一个HTTP GET请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param queryString
	 *            请求的查询参数,可以为null
	 * @param charset
	 *            字符集
	 * @param pretty
	 *            是否美化
	 * @return 返回请求响应的HTML public static void main(String[] args) { String y =
	 *         doGet("https://www.okcoin.com/api/depth.do", null, "UTF8", true);
	 *         System.out.println(y); }
	 */
	public static String getInfos(String url, String queryString,String charset, boolean pretty) {
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		try {
			// 代理上网时设置这个
			// client.getHostConfiguration().setProxy("10.128.7.50", 3128);
			if (StringUtils.isNotBlank(queryString)){
				// 对get请求参数做了http请求默认编码，好像没有任何问题，汉字编码后，就成为%式样的字符串
				method.setQueryString(URIUtil.encodeQuery(queryString));
			}
			/*client.setTimeout(9000);
			client.setConnectionTimeout(9000);*/
			client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);  
			client.getHttpConnectionManager().getParams().setSoTimeout(10000);
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),charset));
				String line;
				while ((line = reader.readLine()) != null) {
					if (pretty){
						response.append(line).append(System.getProperty("line.separator"));
					}else{
						response.append(line);
					}
				}
				reader.close();
			}
		} catch (URIException e) {
			 log.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e);
		} catch (IOException e) {
			 log.error("执行HTTP Get请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}
		return response.toString();
	}
	public static String getOKCoinBalanceInfo(){
		//参数数组
		String result = "";
		Map<String,String> sArray = new HashMap<String, String>();
		sArray.put("partner", Long.toString(Long.parseLong(OkCoinUtil.PARTNER)));
		//对参数数组签名
		String sign = buildMysign(sArray, OkCoinUtil.SECRET_KEY);
	    try {
	    	HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);  
			client.getHttpConnectionManager().getParams().setSoTimeout(10000);
			//post请求
			PostMethod method = new PostMethod(USER_INFO_URI);
			NameValuePair[] data = {
					new NameValuePair("partner", Long.toString(Long.parseLong(OkCoinUtil.PARTNER))),
					new NameValuePair("sign", sign)
			};
			method.setRequestBody(data);
			client.executeMethod(method);
			//返回json结果
			result = method.getResponseBodyAsString();
		} catch (HttpException e) {
			log.error("获取OKCoin的余额信息 Http协议",e);
		} catch (IOException e) {
			log.error("获取OKCoin的余额信息 IO协议",e);
		}
		return result;
	}
	
	public static void testApi(){
		
		//参数数组
		Map<String,String> sArray = new HashMap<String, String>();
//		sArray.put("partner", Long.toString(PARTNER));
		sArray.put("partner", Long.toString(Long.parseLong(OkCoinUtil.PARTNER)));
		//对参数数组签名
		String sign = buildMysign(sArray, OkCoinUtil.SECRET_KEY);
		
	    try {
	    	HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);  
			client.getHttpConnectionManager().getParams().setSoTimeout(10000);
			//post请求
			PostMethod method = new PostMethod(USER_INFO_URI);
			NameValuePair[] data = {
//					new NameValuePair("partner", Long.toString(PARTNER)),
					new NameValuePair("partner", Long.toString(Long.parseLong(OkCoinUtil.PARTNER))),
					new NameValuePair("sign", sign)
			};
			method.setRequestBody(data);
			client.executeMethod(method);
			//返回json结果
			String result = method.getResponseBodyAsString();
			//////////////////////////////////////////////////////////////////////////////////////////
			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			System.out.println(result);
			//////////////////////////////////////////////////////////////////////////////////////////
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) {
		testApi();
	}
}
